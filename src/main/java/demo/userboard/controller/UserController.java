package demo.userboard.controller;

import demo.userboard.dto.request.JoinRequestDto;
import demo.userboard.dto.request.LoginRequestDto;
import demo.userboard.dto.response.JoinResponseDto;
import demo.userboard.dto.response.LoginResponseDto;
import demo.userboard.global.common.response.CommonResponse;
import demo.userboard.global.common.util.ApiResponseUtil;
import demo.userboard.service.UserService;
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
    public CommonResponse<LoginResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto) {

        return ApiResponseUtil.success(userService.login(loginRequestDto));
    }
}
