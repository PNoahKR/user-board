package demo.userboard.global.common.util;

import static org.springframework.http.HttpStatus.OK;

import demo.userboard.global.common.response.CommonResponse;

public class ApiResponseUtil {

    public static final String SUCCESS_MESSAGE = "success";

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(
            OK.value(),
            SUCCESS_MESSAGE,
            null
        );
    }

    public static <T> CommonResponse<T> success(T response) {
        return new CommonResponse<>(
            OK.value(),
            SUCCESS_MESSAGE,
            response
        );
    }
}
