package baemin_backend.common.exception.base.handler;

import baemin_backend.common.exception.base.BadRequestException;
import baemin_backend.common.response.BaseResponse;
import baemin_backend.common.exception.base.InternalServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.servlet.NoHandlerFoundException;

import static baemin_backend.common.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@RestControllerAdvice
public class BaseExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public BaseResponse<Object> handle_BadRequest(BadRequestException e) {
        log.error("[handle_CommonBadRequest]", e);
        return new BaseResponse<>(e.getExceptionStatus());
    }

    // 위와 동일 (return ResponseEntity<>)
    /*
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BaseResponse<Object>> handle_CommonBadRequest(BadRequestException e) {
        log.error("[handle_CommonBadRequest]", e);
        return ResponseEntity.badRequest().body(new BaseResponse<>(e.getExceptionStatus()));
    }
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResponse<Object> handle_NoHandlerFoundException(Exception e) {
        log.error("[handle_NoHandlerFoundException]", e);
        return new BaseResponse<>(URL_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerErrorException.class)
    public BaseResponse<Object> handle_InternalServerError(InternalServerErrorException e) {
        log.error("[handle_InternalServerError]", e);
        return new BaseResponse<>(e.getExceptionStatus());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerError.class)
    public BaseResponse<Object> handle_InternalServerError(Exception e) {
        log.error("[handle_InternalServerError]", e);
        return new BaseResponse<>(SERVER_ERROR);
    }

}
