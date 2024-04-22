package demo.userboard.global.core.exception.handler;


import static demo.userboard.global.common.response.CustomErrorCode.SERVER_ERROR;

import demo.userboard.global.common.response.CommonResponse;
import demo.userboard.global.common.response.CustomErrorCode;
import demo.userboard.global.common.util.ApiResponseUtil;
import demo.userboard.global.core.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 추가해야 하는 Exception 추가 정의

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonResponse<Object>> handleException(Exception exception) {
        log.error(
            "Exception",
            exception
        );

        return toErrorResponseEntity(SERVER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<CommonResponse<Object>> handleBadRequestException(CustomException exception) {
        log.warn(
            "CustonException",
            exception
        );

        return toErrorResponseEntity(exception.getErrorCode());
    }

    protected ResponseEntity<CommonResponse<Object>> toErrorResponseEntity(CustomErrorCode errorCode) {

        return ResponseEntity
            .status(errorCode.getStatus().value())
            .body(ApiResponseUtil.failure(errorCode));
    }
}