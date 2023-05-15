package baemin_backend.common.exception.handler;

import baemin_backend.common.exception.BadRequestException;
import baemin_backend.common.response.BaseErrorResponse;
import baemin_backend.common.response.BaseResponse;
import baemin_backend.common.exception.InternalServerErrorException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
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
    public BaseErrorResponse handle_BadRequest(BadRequestException e) {
        log.error("[handle_BadRequest]", e);
        return new BaseErrorResponse(e.getExceptionStatus());
    }

    // 위와 동일 (return ResponseEntity<>)
    /*
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BaseErrorResponse> handle_BadRequest(BadRequestException e) {
        log.error("[handle_BadRequest]", e);
        return ResponseEntity.badRequest().body(new BaseErrorResponse(e.getExceptionStatus()));
    }
     */

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerErrorException.class)
    public BaseErrorResponse handle_InternalServerError(InternalServerErrorException e) {
        log.error("[handle_InternalServerError]", e);
        return new BaseErrorResponse(e.getExceptionStatus());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseErrorResponse handle_NoHandlerFoundException(Exception e) {
        log.error("[handle_NoHandlerFoundException]", e);
        return new BaseErrorResponse(URL_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TypeMismatchException.class)
    public BaseResponse<Object> handle_TypeMismatchException(TypeMismatchException e) {
        log.error("[handle_TypeMismatchException]", e);
        return new BaseResponse<>(URL_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseErrorResponse handle_ConstraintViolationException(ConstraintViolationException e) {
        log.error("[handle_ConstraintViolationException]", e);
        return new BaseErrorResponse(BAD_REQUEST, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataAccessException.class)
    public BaseErrorResponse handle_DataAccessException(DataAccessException e) {
        log.error("[handle_DataAccessException]", e);
        return new BaseErrorResponse(DATABASE_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public BaseErrorResponse handle_RuntimeException(Exception e) {
        log.error("[handle_RuntimeException]", e);
        return new BaseErrorResponse(SERVER_ERROR);
    }

}
