package baemin_backend.common.exception.jwt;

import baemin_backend.common.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class JwtExpiredTokenException extends JwtUnauthorizedTokenException {

    private final ResponseStatus exceptionStatus;

    public JwtExpiredTokenException(ResponseStatus exceptionStatus) {
        super(exceptionStatus);
        this.exceptionStatus = exceptionStatus;
    }

}
