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

        return ResponseEntity.ok(userService.join(joinRequestDto));
    }

}
