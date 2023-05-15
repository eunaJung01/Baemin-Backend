package baemin_backend.common.response;

import baemin_backend.common.response.status.BaseResponseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;

import static baemin_backend.common.response.status.SuccessResponseStatus.*;

@Getter
@JsonPropertyOrder({"code", "status", "message", "result", "timestamp"})
public class BaseResponse<T> implements BaseResponseStatus {

    private final int code;
    private final int status;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T result;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final LocalDateTime timestamp;

    public BaseResponse(T result) {
        this.code = SUCCESS.getCode();
        this.status = SUCCESS.getStatus();
        this.message = SUCCESS.getMessage();
        this.result = result;
        this.timestamp = null;
    }

    public BaseResponse(BaseResponseStatus status) {
        this.code = status.getCode();
        this.status = status.getStatus();
        this.message = status.getMessage();
        this.result = null;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
