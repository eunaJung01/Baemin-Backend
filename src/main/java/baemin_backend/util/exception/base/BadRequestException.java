package baemin_backend.util.exception.base;

import baemin_backend.util.response_status.BaseResponseStatus;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private final BaseResponseStatus exceptionStatus;

    public BadRequestException(BaseResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

}
