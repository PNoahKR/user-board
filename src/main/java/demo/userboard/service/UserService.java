package demo.userboard.service;

import demo.userboard.dto.request.InfoUpdateRequestDto;
import demo.userboard.dto.request.JoinRequestDto;
import demo.userboard.dto.request.LoginRequestDto;
import demo.userboard.dto.response.InfoUpdateResponseDto;
import demo.userboard.dto.response.JoinResponseDto;
import demo.userboard.dto.response.UserResponseDto;

public interface UserService {
    JoinResponseDto join(JoinRequestDto requestDto);

    Long login(LoginRequestDto requestDto);

    UserResponseDto findUser(Long id);

    InfoUpdateResponseDto userInfoUpdate(Long id, InfoUpdateRequestDto requestDto);
}
