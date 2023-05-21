package baemin_backend.common.exception_handler;

import baemin_backend.common.exception.JwtExpiredTokenException;
import baemin_backend.common.exception.JwtInvalidAccessTokenException;
import baemin_backend.common.exception.JwtNoTokenException;
import baemin_backend.common.exception.JwtUnauthorizedTokenException;
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
    @ExceptionHandler(JwtNoTokenException.class)
    public BaseErrorResponse handle_JwtNoTokenException(JwtNoTokenException e) {
        log.error("[handle_JwtNoTokenException]", e);
        return new BaseErrorResponse(e.getExceptionStatus());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JwtExpiredTokenException.class)
    public BaseErrorResponse handle_JwtExpiredTokenException(JwtExpiredTokenException e) {
        log.error("[handle_JwtExpiredTokenException]", e);
        return new BaseErrorResponse(e.getExceptionStatus());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtUnauthorizedTokenException.class)
    public BaseErrorResponse handle_JwtUnauthorizedTokenException(JwtUnauthorizedTokenException e) {
        log.error("[handle_JwtUnauthorizedTokenException]", e);
        return new BaseErrorResponse(e.getExceptionStatus());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JwtInvalidAccessTokenException.class)
    public BaseErrorResponse handle_JwtInvalidAccessTokenException(JwtInvalidAccessTokenException e) {
        log.error("[handle_JwtInvalidAccessTokenException]", e);
        return new BaseErrorResponse(e.getExceptionStatus());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(JwtException.class)
    public BaseErrorResponse handle_JwtException(Exception e) {
        log.error("[handle_JwtException]", e);
        return new BaseErrorResponse(JWT_ERROR);
    }

}
