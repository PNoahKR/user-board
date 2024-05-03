package demo.userboard.controller;

import demo.userboard.SessionConst;
import demo.userboard.dto.request.JoinRequestDto;
import demo.userboard.dto.request.LoginRequestDto;
import demo.userboard.dto.response.JoinResponseDto;
import demo.userboard.dto.response.UserResponseDto;
import demo.userboard.global.common.response.CommonResponse;
import demo.userboard.global.common.response.CustomErrorCode;
import demo.userboard.global.common.util.ApiResponseUtil;
import demo.userboard.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public CommonResponse<JoinResponseDto> joinUser(@RequestBody JoinRequestDto joinRequestDto) {

        return ApiResponseUtil.success(userService.join(joinRequestDto));
    }

    @PostMapping("/login")
    public CommonResponse<Long> loginUser(@RequestBody LoginRequestDto loginRequestDto, HttpSession session) {
        Long userId = userService.login(loginRequestDto);

        session.setAttribute(SessionConst.LOGIN_USER, userId);
        session.setMaxInactiveInterval(600);

        return ApiResponseUtil.success(userId);
    }

    @GetMapping("/user/info")
    public CommonResponse<UserResponseDto> userInfo(@SessionAttribute(value = SessionConst.LOGIN_USER, required = false) Long loginUser) {
        if (loginUser == null) {
            ApiResponseUtil.failure(CustomErrorCode.UNAUTHORIZED);
        }

        return ApiResponseUtil.success(userService.findUser(loginUser));
    }

    @PostMapping("/logout")
    public CommonResponse<?> logout(HttpSession session) {
        session.invalidate();
        return ApiResponseUtil.success();
    }
}
