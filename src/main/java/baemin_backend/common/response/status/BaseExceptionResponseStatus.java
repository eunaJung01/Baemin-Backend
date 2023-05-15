package baemin_backend.common.response.status;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum BaseExceptionResponseStatus implements BaseResponseStatus {

    /**
     * 2000: Request 오류 (BAD_REQUEST)
     */
    URL_NOT_FOUND(2000, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 URL 입니다."),
    BAD_PARAMETER(2001, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 Parameter 입니다."),

    /**
     * 3000: Server, Database 오류 (INTERNAL_SERVER_ERROR)
     */
    SERVER_ERROR(3000, HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버에서 오류가 발생하였습니다."),
    DATABASE_ERROR(3001, HttpStatus.INTERNAL_SERVER_ERROR.value(), "데이터베이스에서 오류가 발생하였습니다.");

    private final int code;
    private final int status;
    private final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
