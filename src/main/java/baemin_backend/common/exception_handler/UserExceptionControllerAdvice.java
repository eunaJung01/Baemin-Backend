package baemin_backend.common.exception_handler;

import baemin_backend.common.exception.UserException;
import baemin_backend.common.response.BaseErrorResponse;
import jakarta.annotation.Priority;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static baemin_backend.common.response.status.BaseExceptionResponseStatus.INVALID_USER_VALUE;

@Slf4j
@Priority(0)
@RestControllerAdvice
public class UserExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    public BaseErrorResponse handle_UserException(UserException e) {
        log.error("[handle_UserException]", e);
        return new BaseErrorResponse(INVALID_USER_VALUE, e.getMessage());
    }

}
