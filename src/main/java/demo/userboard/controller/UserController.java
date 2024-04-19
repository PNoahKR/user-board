package demo.userboard.controller;

import demo.userboard.domain.User;
import demo.userboard.dto.request.JoinRequestDto;
import demo.userboard.dto.response.JoinResponseDto;
import demo.userboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<JoinResponseDto> joinUser(@RequestBody JoinRequestDto joinRequestDto) {
        User newUser = joinRequestDto.toEntity();
        userService.join(newUser);
        if (newUser != null) {
            JoinResponseDto joinResponseDto = new JoinResponseDto("회원가입에 성공했습니다.", newUser.getId(), HttpStatus.OK.value());
            return ResponseEntity.ok(joinResponseDto);
        } else {
            JoinResponseDto joinResponseDto = new JoinResponseDto("회원가입에 실패했습니다. 입력하신 정보를 확인해주세요.", null, HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(joinResponseDto);
        }
    }

}
