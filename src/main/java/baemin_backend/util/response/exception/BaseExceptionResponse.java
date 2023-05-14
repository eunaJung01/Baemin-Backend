package baemin_backend.util.response.exception;

import baemin_backend.util.response.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"isSuccess", "code", "message"})
public class BaseExceptionResponse {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    private final int code;

    private final String message;

    public BaseExceptionResponse(ResponseStatus status) {
        this.isSuccess = status.isSuccess();
        this.code = status.getCode();
        this.message = status.getMessage();
    }

}
