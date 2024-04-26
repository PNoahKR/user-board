package demo.userboard.global.core.exception;

import demo.userboard.global.common.response.CustomErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final CustomErrorCode errorCode;

    public CustomException(CustomErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
