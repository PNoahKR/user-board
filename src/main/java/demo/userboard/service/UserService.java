package demo.userboard.service;

import demo.userboard.domain.User;
import demo.userboard.dto.request.JoinRequestDto;
import demo.userboard.dto.request.LoginRequestDto;
import demo.userboard.dto.response.JoinResponseDto;
import demo.userboard.dto.response.LoginResponseDto;

import java.util.Optional;

public interface UserService {
    JoinResponseDto join(JoinRequestDto requestDto);

    LoginResponseDto login(LoginRequestDto requestDto);

    Optional<User> findUser(String userEmail);
}
