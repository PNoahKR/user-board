package demo.userboard.service;

import demo.userboard.dto.request.JoinRequestDto;
import demo.userboard.dto.request.LoginRequestDto;
import demo.userboard.dto.response.JoinResponseDto;

public interface UserService {
    JoinResponseDto join(JoinRequestDto requestDto);

    Long login(LoginRequestDto requestDto);

}
