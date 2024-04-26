package demo.userboard.controller;

import demo.userboard.global.common.response.CommonResponse;
import demo.userboard.global.common.response.CustomErrorCode;
import demo.userboard.global.core.exception.CustomException;
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

    @GetMapping("/test3")
    public CommonResponse<?> test3() {
        if (true) {
            throw new CustomException(CustomErrorCode.API_RESPONSE_ERROR);
        }
        return success();
    }

    @GetMapping("/test4")
    public CommonResponse<?> test4() {
        int a = 1 / 0;
        return success();
    }
}
