package demo.userboard.service;

import demo.userboard.domain.User;
import demo.userboard.dto.request.InfoUpdateRequestDto;
import demo.userboard.dto.request.JoinRequestDto;
import demo.userboard.dto.request.LoginRequestDto;
import demo.userboard.dto.response.JoinResponseDto;
import demo.userboard.dto.response.UserResponseDto;
import demo.userboard.global.common.crypto.PasswordCrypto;
import demo.userboard.global.common.response.CustomErrorCode;
import demo.userboard.global.core.exception.CustomException;
import demo.userboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordCrypto passwordCrypto;


    @Override
    @Transactional
    public JoinResponseDto join(JoinRequestDto requestDto) {
        User user = requestDto.toEntity();
        validateDuplicateUser(user);
        User savedUser = userRepository.save(user);

        return new JoinResponseDto("성공", savedUser.getId(), 200);
    }

    @Override
    public Long login(LoginRequestDto requestDto) {
        String encodePassword = passwordCrypto.encodePassword(requestDto.getPassword());
        return userRepository.findByEmail(requestDto.getEmail())
                .filter(user -> passwordCrypto.matches(requestDto.getPassword(), encodePassword))
                .map(User::getId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.LOGIN_FAIL));
    }

    @Override
    public UserResponseDto findUser(Long id) {
        return userRepository.findById(id)
                .map(UserResponseDto::from)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_LOGIN_INFO));
    }

    @Override
    @Transactional
    public Long userInfoUpdate(Long id, InfoUpdateRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_LOGIN_INFO));

        String newNickname = requestDto.getNickname();
        String newPassword = requestDto.getPassword();

        if (!StringUtils.hasText(newNickname) && !StringUtils.hasText(newPassword)) {
            throw new CustomException(CustomErrorCode.INVALID_FORMAT);
        }

        if (StringUtils.hasText(newNickname)) {
            validateUpdateField(newNickname, 1, 6);
            userRepository.findByNickname(newNickname)
                    .ifPresent(u -> {
                        throw new CustomException(CustomErrorCode.DUPLICATE_VALUE);
                    });
            user.updateNickname(newNickname);
        }

        if (StringUtils.hasText(newPassword)) {
            validateUpdateField(newPassword, 4, 20);
            user.updatePassword(newPassword);
        }

        return id;
    }


    private void validateDuplicateUser(User user) {
        userRepository.findByEmailOrNickname(user.getEmail(), user.getNickname())
                .ifPresent(a -> {
                    if (a.getEmail().equals(user.getEmail())) {
                        throw new CustomException(CustomErrorCode.DUPLICATE_VALUE);
                    }
                    if (a.getNickname().equals(user.getNickname())) {
                        throw new CustomException(CustomErrorCode.DUPLICATE_VALUE);
                    }
                });
    }

    private void validateUpdateField(String field, int minLength, int maxLength) {
        if (field.isEmpty()
                || field.length() < minLength
                || field.length() > maxLength
                || !field.equals(field.trim())) {
            throw new CustomException(CustomErrorCode.INVALID_FORMAT);
        }
    }
}