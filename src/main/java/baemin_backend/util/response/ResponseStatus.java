package baemin_backend.util.response;

import lombok.Getter;

@Getter
public enum ResponseStatus {

    /**
     * 1000: 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /**
     * 2000: Request 오류
     */
    NOT_FOUND(false, 2000, "잘못된 URL 요청입니다."),
    BAD_PARAM_REQUEST(false, 2001, "잘못된 Parameter 요청입니다."),

    /**
     * 3000: Server, Database 오류
     */
    INTERNAL_SERVER_ERROR(false, 3000, "서버에서 오류가 발생하였습니다."),
    DATABASE_ERROR(false, 3001, "데이터베이스에서 오류가 발생하였습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    ResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }

}
