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
    LOGIN_FAIL(HttpStatus.BAD_REQUEST, "로그인 실패"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "계정을 찾을 수 없습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증 실패");

    private final HttpStatus status;
    private final String message;

    CustomErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
