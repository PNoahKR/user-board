package demo.userboard.controller;

import demo.userboard.SessionConst;
import demo.userboard.dto.request.JoinRequestDto;
import demo.userboard.dto.request.LoginRequestDto;
import demo.userboard.dto.response.JoinResponseDto;
import demo.userboard.dto.response.LoginResponseDto;
import demo.userboard.global.common.response.CommonResponse;
import demo.userboard.global.common.util.ApiResponseUtil;
import demo.userboard.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public CommonResponse<JoinResponseDto> joinUser(@RequestBody JoinRequestDto joinRequestDto) {

        return ApiResponseUtil.success(userService.join(joinRequestDto));
    }

    @PostMapping("/login")
    public CommonResponse<LoginResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto, HttpSession session) {
        LoginResponseDto responseDto = userService.login(loginRequestDto);

        session.setAttribute(SessionConst.LOGIN_USER, responseDto);
        session.setMaxInactiveInterval(600);

        return ApiResponseUtil.success(responseDto);
    }
}
