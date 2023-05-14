package baemin_backend.util.exception.handler;

import baemin_backend.util.exception.BaseException;
import baemin_backend.util.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

import static baemin_backend.util.BaseResponseStatus.*;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(BaseException.class)
    public BaseResponse<Object> handle_BaseException(BaseException e) {
        log.error("[handle_BaseException]", e);
        return new BaseResponse<>(e.getStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public BaseResponse<Object> handle_IllegalArgumentException(Exception e) {
        log.error("[handle_IllegalArgumentException]", e);
        return new BaseResponse<>(BAD_PARAM_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public BaseResponse<Object> handle_404(Exception e) {
        log.error("[handle_404]", e);
        return new BaseResponse<>(NOT_FOUND);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public BaseResponse<Object> handle_500(Exception e) {
        log.error("[handle_500]", e);
        return new BaseResponse<>(INTERNAL_SERVER_ERROR);
    }

}
