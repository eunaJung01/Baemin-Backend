package baemin_backend.util.response.exception;

import baemin_backend.util.response.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends Exception {

    private ResponseStatus status;

}
