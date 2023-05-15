package baemin_backend.util.exception.base;

import baemin_backend.util.response_status.BaseResponseStatus;
import lombok.Getter;

@Getter
public class InternalServerErrorException extends RuntimeException {

    private final BaseResponseStatus exceptionStatus;

    public InternalServerErrorException(BaseResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

}
