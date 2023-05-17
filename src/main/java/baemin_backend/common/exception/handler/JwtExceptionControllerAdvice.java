package baemin_backend.common.exception.handler;

import baemin_backend.common.response.BaseErrorResponse;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static baemin_backend.common.response.status.BaseExceptionResponseStatus.JWT_ERROR;

@Slf4j
@RestControllerAdvice
public class JwtExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(JwtException.class)
    public BaseErrorResponse handle_JwtException(Exception e) {
        log.error("[handle_JwtException]", e);
        return new BaseErrorResponse(JWT_ERROR);
    }

}
