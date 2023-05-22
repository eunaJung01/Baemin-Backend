package baemin_backend.lecture_examples;

import baemin_backend.common.response.BaseErrorResponse;
import baemin_backend.common.response.BaseResponse;
import baemin_backend.lecture_examples.reqbody_resbody.UserData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static baemin_backend.common.response.status.BaseExceptionResponseStatus.BAD_REQUEST;

@RestController
public class BaseResponseController {

    @RequestMapping("/base-response")
    public BaseResponse<UserData> checkBaseResponse() {
        UserData userData = new UserData("aralla", 23);
        return new BaseResponse<>(userData);
    }

    @RequestMapping("/base-error-response")
    public BaseErrorResponse checkBaseErrorResponse() {
        return new BaseErrorResponse(BAD_REQUEST);
    }

}
