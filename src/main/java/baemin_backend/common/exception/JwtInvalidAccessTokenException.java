package baemin_backend.common.exception;

import baemin_backend.common.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class JwtInvalidAccessTokenException extends RuntimeException {

    private final ResponseStatus exceptionStatus;

    public JwtInvalidAccessTokenException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

}
