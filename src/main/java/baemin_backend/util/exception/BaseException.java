package baemin_backend.util.exception;

import baemin_backend.util.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends Exception {

    private ResponseStatus status;

}
