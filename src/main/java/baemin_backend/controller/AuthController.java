package baemin_backend.controller;

import baemin_backend.common.argument_resolver.PreAuthorize;
import baemin_backend.common.exception.UserException;
import baemin_backend.common.exception.jwt.JwtAuthFailedException;
import baemin_backend.common.response.BaseResponse;
import baemin_backend.dto.auth.Auth;
import baemin_backend.dto.auth.LoginRequest;
import baemin_backend.dto.auth.LoginResponse;
import baemin_backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static baemin_backend.common.response.status.BaseExceptionResponseStatus.INVALID_USER_VALUE;
import static baemin_backend.common.response.status.BaseExceptionResponseStatus.TOKEN_MISMATCH;
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
    public BaseResponse<LoginResponse> login(@Validated @RequestBody LoginRequest authRequest, BindingResult bindingResult,
                                             @PreAuthorize Auth auth) {
        log.info("[AuthController.login] userId={}", auth.getUserId());
        if (bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }

        // TODO: 이메일 일치 확인
        if (!authRequest.getEmail().equals(auth.getEmail())) {
            throw new JwtAuthFailedException(TOKEN_MISMATCH);
        }
        return new BaseResponse<>(authService.login(authRequest, auth.getUserId()));
    }

}
