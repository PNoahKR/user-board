package demo.userboard.service;

import demo.userboard.domain.User;
import demo.userboard.dto.request.JoinRequestDto;
import demo.userboard.dto.response.JoinResponseDto;
import demo.userboard.repository.UserRepository;
import org.hibernate.mapping.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public Optional<User> findUser(Long userId) {
        return userRepository.findById(userId);
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