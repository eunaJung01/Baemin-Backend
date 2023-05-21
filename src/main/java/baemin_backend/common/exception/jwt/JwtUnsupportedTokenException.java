package baemin_backend.common.exception.jwt;

import baemin_backend.common.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class JwtUnsupportedTokenException extends RuntimeException {

    private final ResponseStatus exceptionStatus;

    public JwtUnsupportedTokenException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }

}
