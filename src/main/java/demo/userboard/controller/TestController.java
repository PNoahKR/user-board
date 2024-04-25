package demo.userboard.controller;

import demo.userboard.global.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static demo.userboard.global.common.util.ApiResponseUtil.success;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/test")
    public CommonResponse<String> test() {
        return success("성공 응답값 테스트");
    }

    @GetMapping("/test2")
    public CommonResponse<String> test2() {
        return success();
    }
}
