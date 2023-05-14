package baemin_backend.config.response.error;

import baemin_backend.config.response.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends Exception {

    private ResponseStatus status;

}
