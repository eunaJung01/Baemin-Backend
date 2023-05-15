package baemin_backend.common.exception.base;

import baemin_backend.common.response.status.BaseResponseStatus;
import lombok.Getter;

@Getter
public class InternalServerErrorException extends RuntimeException {

    private final BaseResponseStatus exceptionStatus;

    public InternalServerErrorException(BaseResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

}
