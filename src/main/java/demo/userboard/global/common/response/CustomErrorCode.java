package demo.userboard.global.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Getter
public enum CustomErrorCode {
    // 공통 응답 코드
    SUCCESS(OK, "success"),
    SERVER_ERROR(INTERNAL_SERVER_ERROR, "server error"),
    // 사용자 정의 코드
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "login failed");

    private final HttpStatus status;
    private final String message;

    CustomErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
