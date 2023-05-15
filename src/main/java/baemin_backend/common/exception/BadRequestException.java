package baemin_backend.common.exception;

import baemin_backend.common.response.status.BaseResponseStatus;
import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private final BaseResponseStatus exceptionStatus;

    public BadRequestException(BaseResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

}
