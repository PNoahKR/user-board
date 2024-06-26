package demo.userboard.global.core.exception.handler;

import demo.userboard.global.common.response.CommonResponse;
import demo.userboard.global.common.response.CustomErrorCode;
import demo.userboard.global.common.util.ApiResponseUtil;
import demo.userboard.global.core.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static demo.userboard.global.common.response.CustomErrorCode.SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // 추가해야 하는 Exception 추가 정의

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<CommonResponse<Object>> handleException(Exception exception) {
        log.error("Exception", exception);

        return toErrorResponseEntity(SERVER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<CommonResponse<Object>> handleBadRequestException(CustomException exception) {
        log.warn("CustomException", exception);

        return toErrorResponseEntity(exception.getErrorCode());
    }

    protected ResponseEntity<CommonResponse<Object>> toErrorResponseEntity(CustomErrorCode errorCode) {

        return ResponseEntity
                .status(errorCode.getStatus().value())
                .body(ApiResponseUtil.failure(errorCode));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        log.error("Exception", exception);
        CommonResponse<Object> response = ApiResponseUtil.failure(statusCode.value(), exception.getMessage());
        return ResponseEntity.status(statusCode).body(response);
    }
}
