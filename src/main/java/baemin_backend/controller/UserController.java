package baemin_backend.controller;

import baemin_backend.common.argument_resolver.PreAuthorize;
import baemin_backend.common.exception.UserException;
import baemin_backend.common.response.BaseResponse;
import baemin_backend.dto.user.PostLoginResponse;
import baemin_backend.dto.user.PostUserRequest;
import baemin_backend.dto.user.PostUserResponse;
import baemin_backend.service.UserService;
import baemin_backend.dto.user.PostLoginRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static baemin_backend.common.response.status.BaseExceptionResponseStatus.INVALID_USER_VALUE;
import static baemin_backend.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public BaseResponse<PostUserResponse> signUp(@Validated @RequestBody PostUserRequest postUserRequest, BindingResult bindingResult) {
        log.info("[UserController.signUp]");
        if (bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(userService.createUser(postUserRequest));
    }

    @PostMapping("/login")
    public BaseResponse<PostLoginResponse> login(@Validated @RequestBody PostLoginRequest postLoginRequest, BindingResult bindingResult,
                                                 @PreAuthorize long userId) {
        log.info("[UserController.login] userId={}", userId);
        if (bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(userService.login(postLoginRequest, userId));
    }

}
