package baemin_backend.common.exception.handler;

import baemin_backend.common.exception.base.BadRequestException;
import baemin_backend.common.response.BaseResponse;
import baemin_backend.common.exception.base.InternalServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import static baemin_backend.common.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@RestControllerAdvice
public class BaseExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public BaseResponse<Object> handle_BadRequest(BadRequestException e) {
        log.error("[handle_BadRequest]", e);
        return new BaseResponse<>(e.getExceptionStatus());
    }

    // 위와 동일 (return ResponseEntity<>)
    /*
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BaseResponse<Object>> handle_BadRequest(BadRequestException e) {
        log.error("[handle_BadRequest]", e);
        return ResponseEntity.badRequest().body(new BaseResponse<>(e.getExceptionStatus()));
    }
     */

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerErrorException.class)
    public BaseResponse<Object> handle_InternalServerError(InternalServerErrorException e) {
        log.error("[handle_InternalServerError]", e);
        return new BaseResponse<>(e.getExceptionStatus());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResponse<Object> handle_NoHandlerFoundException(Exception e) {
        log.error("[handle_NoHandlerFoundException]", e);
        return new BaseResponse<>(URL_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    public BaseResponse<Object> handle_DataAccessException(DataAccessException e) {
        log.error("[handle_DataAccessException]", e);
        return new BaseResponse<>(DATABASE_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<Object> handle_RuntimeException(Exception e) {
        log.error("[handle_RuntimeException]", e);
        return new BaseResponse<>(SERVER_ERROR);
    }

}
