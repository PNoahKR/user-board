package demo.userboard.controller;

import demo.userboard.dto.request.JoinRequestDto;
import demo.userboard.dto.request.LoginRequestDto;
import demo.userboard.dto.response.JoinResponseDto;
import demo.userboard.dto.response.LoginResponseDto;
import demo.userboard.global.common.response.CustomErrorCode;
import demo.userboard.global.core.exception.CustomException;
import demo.userboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<JoinResponseDto> joinUser(@RequestBody JoinRequestDto joinRequestDto) {

        return ResponseEntity.ok(userService.join(joinRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto responseDto = userService.login(loginRequestDto);
        if (responseDto == null) {
            throw new CustomException(CustomErrorCode.UNAUTHORIZED);
        } else {
            return ResponseEntity.ok(responseDto);
        }
    }
}
