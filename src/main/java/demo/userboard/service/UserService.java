package demo.userboard.service;

import demo.userboard.dto.request.JoinRequestDto;
import demo.userboard.dto.request.LoginRequestDto;
import demo.userboard.dto.response.JoinResponseDto;
import demo.userboard.dto.response.LoginResponseDto;

public interface UserService {
    JoinResponseDto join(JoinRequestDto requestDto);

    LoginResponseDto login(LoginRequestDto requestDto);

}
