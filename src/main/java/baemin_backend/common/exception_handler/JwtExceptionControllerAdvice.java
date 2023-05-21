package baemin_backend.common.exception_handler;

import baemin_backend.common.exception.jwt.*;
import baemin_backend.common.response.BaseErrorResponse;
import io.jsonwebtoken.JwtException;
import jakarta.annotation.Priority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static baemin_backend.common.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@Priority(0)
@RestControllerAdvice
public class JwtExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({JwtNoTokenException.class, JwtUnsupportedTokenException.class})
    public BaseErrorResponse handle_JwtBadRequestException(JwtNoTokenException e) {
        log.error("[handle_JwtBadRequestException]", e);
        return new BaseErrorResponse(e.getExceptionStatus());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({JwtExpiredTokenException.class, JwtInvalidTokenException.class, JwtMalformedTokenException.class, JwtUnauthorizedTokenException.class})
    public BaseErrorResponse handle_JwtUnauthorizedException(JwtUnauthorizedTokenException e) {
        log.error("[handle_JwtUnauthorizedException]", e);
        return new BaseErrorResponse(e.getExceptionStatus());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(JwtException.class)
    public BaseErrorResponse handle_JwtException(Exception e) {
        log.error("[handle_JwtException]", e);
        return new BaseErrorResponse(JWT_ERROR);
    }

}
