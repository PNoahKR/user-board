package demo.userboard.service;

import demo.userboard.domain.User;
import demo.userboard.dto.request.JoinRequestDto;
import demo.userboard.dto.response.JoinResponseDto;

import java.util.Optional;

public interface UserService {
    JoinResponseDto join(JoinRequestDto requestDto);

    Optional<User> findUser(Long userId);
}
