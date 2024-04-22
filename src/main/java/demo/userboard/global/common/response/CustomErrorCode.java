package demo.userboard.global.common.response;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CustomErrorCode {

    // 공통 응답 코드
    SUCCESS(
        OK,
        "success"
    ),
    SERVER_ERROR(
        INTERNAL_SERVER_ERROR,
        "server error"
    ),

    // 사용자 정의 코드
    API_RESPONSE_ERROR(
        INTERNAL_SERVER_ERROR,
        "API 요청에 실패하였습니다"
    );


    private final HttpStatus status;
    private final String message;

    CustomErrorCode(
        HttpStatus status,
        String message
    ) {
        this.status = status;
        this.message = message;
    }
}
