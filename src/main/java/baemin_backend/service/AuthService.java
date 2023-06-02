package baemin_backend.service;

import baemin_backend.common.exception.UserException;
import baemin_backend.common.exception.jwt.JwtAuthFailedException;
import baemin_backend.dao.UserDao;
import baemin_backend.dto.auth.LoginRequest;
import baemin_backend.dto.auth.LoginResponse;
import baemin_backend.util.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static baemin_backend.common.response.status.BaseExceptionResponseStatus.PASSWORD_NO_MATCH;
import static baemin_backend.common.response.status.BaseExceptionResponseStatus.TOKEN_MISMATCH;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(LoginRequest authRequest, long userId) {
        log.info("[AuthService.login]");

        // TODO: 1. 비밀번호 일치 확인
        validatePassword(authRequest.getPassword(), userId);

        // TODO: 2. JWT 갱신
        String updatedJwt = jwtTokenProvider.createToken(authRequest.getEmail(), userId);

        return new LoginResponse(userId, updatedJwt);
    }

    private void validatePassword(String password, long userId) {
        String encodedPassword = userDao.getPasswordByUserId(userId);
        if (!passwordEncoder.matches(password, encodedPassword)) {
            throw new UserException(PASSWORD_NO_MATCH);
        }
    }

    public long getUserIdByEmail(String email) {
        try {
            return userDao.getUserIdByEmail(email);
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new JwtAuthFailedException(TOKEN_MISMATCH);
        }
    }

}
