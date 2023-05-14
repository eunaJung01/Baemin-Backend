package baemin_backend.util.exception.handler;

import baemin_backend.util.exception.BaseException;
import baemin_backend.util.response.BaseExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import static baemin_backend.util.ResponseStatus.*;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(BaseException.class)
    public BaseExceptionResponse handle_BaseException(Exception e) {
        log.error("[handle_BaseException]", e);
        BaseException exception = (BaseException) e;
        return new BaseExceptionResponse(exception.getStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public BaseExceptionResponse handle_IllegalArgumentException(Exception e) {
        log.error("[handle_IllegalArgumentException]", e);
        return new BaseExceptionResponse(BAD_PARAM_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public BaseExceptionResponse handle_404(Exception e) {
        log.error("[handle_404]", e);
        return new BaseExceptionResponse(NOT_FOUND);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public BaseExceptionResponse handle_500(Exception e) {
        log.error("[handle_500]", e);
        return new BaseExceptionResponse(INTERNAL_SERVER_ERROR);
    }

}
