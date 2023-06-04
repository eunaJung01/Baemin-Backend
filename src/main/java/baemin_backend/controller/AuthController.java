package baemin_backend.controller;

import baemin_backend.common.argument_resolver.PreAuthorize;
import baemin_backend.common.exception.UserException;
import baemin_backend.common.response.BaseResponse;
import baemin_backend.dto.auth.LoginRequest;
import baemin_backend.dto.auth.LoginResponse;
import baemin_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static baemin_backend.common.response.status.BaseExceptionResponseStatus.INVALID_USER_VALUE;
import static baemin_backend.util.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * 로그인
     */
    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@Validated @RequestBody LoginRequest authRequest, BindingResult bindingResult) {
        log.info("[AuthController.login]");
        if (bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        return new BaseResponse<>(authService.login(authRequest));
    }

    @GetMapping("")
    public BaseResponse<String> checkAuthorization(@PreAuthorize long userId) {
        log.info("[AuthController.checkAuthorization]");
        return new BaseResponse<>("userId=" + userId);
    }

}
