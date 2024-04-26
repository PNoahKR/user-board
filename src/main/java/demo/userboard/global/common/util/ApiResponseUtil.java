package demo.userboard.global.common.util;

import demo.userboard.global.common.response.CommonResponse;
import demo.userboard.global.common.response.CustomErrorCode;

import static demo.userboard.global.common.response.CustomErrorCode.SUCCESS;

public class ApiResponseUtil {

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(SUCCESS.getStatus().value(), SUCCESS.getMessage(), null);
    }

    public static <T> CommonResponse<T> success(T response) {
        return new CommonResponse<>(SUCCESS.getStatus().value(), SUCCESS.getMessage(), response);
    }

    public static <T> CommonResponse<T> failure(CustomErrorCode errorCode) {
        return new CommonResponse<>(errorCode.getStatus().value(), errorCode.getMessage());
    }
}
