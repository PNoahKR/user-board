package demo.userboard.service;

import demo.userboard.domain.User;
import demo.userboard.dto.request.JoinRequestDto;
import demo.userboard.dto.request.LoginRequestDto;
import demo.userboard.dto.response.JoinResponseDto;
import demo.userboard.dto.response.LoginResponseDto;
import demo.userboard.global.common.response.CustomErrorCode;
import demo.userboard.global.core.exception.CustomException;
import demo.userboard.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public JoinResponseDto join(JoinRequestDto requestDto) {
        User user = requestDto.toEntity();
        validateDuplicateUser(user);
        User savedUser = userRepository.save(user);

        return new JoinResponseDto("성공", savedUser.getId(), 200);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto requestDto) {
        return userRepository.findByEmail(requestDto.getEmail())
                .filter(user -> user.getPassword().equals(requestDto.getPassword()))
                .map(LoginResponseDto::from)
                .orElseThrow(() -> new CustomException(CustomErrorCode.UNAUTHORIZED));
    }


    private void validateDuplicateUser(User user) {
        userRepository.findByEmailOrNickname(user.getEmail(), user.getNickname())
                .ifPresent(a -> {
                    if (a.getEmail().equals(user.getEmail())) {
                        throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
                    }
                    if (a.getNickname().equals(user.getNickname())) {
                        throw new IllegalArgumentException("이미 존재하는 닉네임 입니다.");
                    }
                });
    }
}