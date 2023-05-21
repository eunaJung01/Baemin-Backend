package baemin_backend.common.interceptor;

import baemin_backend.common.exception.jwt.*;
import baemin_backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import baemin_backend.util.jwt.JwtTokenProvider;

import static baemin_backend.common.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthInterceptor implements HandlerInterceptor {

    private static final String JWT_TOKEN_PREFIX = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("[JwtAuthInterceptor.preHandle]");

        String accessToken = resolveAccessToken(request);
        validateAccessToken(accessToken);

        String email = jwtTokenProvider.getPrincipal(accessToken);
        long userId = jwtTokenProvider.getUserId(accessToken);
        validatePayload(email, userId);

        request.setAttribute("userId", userId);
        return true;
    }

    private String resolveAccessToken(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        validateToken(token);
        return token.substring(JWT_TOKEN_PREFIX.length());
    }

    private void validateToken(String token) {
        if (token == null) {
            throw new JwtNoTokenException(TOKEN_NOT_FOUND);
        }
        if (!token.startsWith(JWT_TOKEN_PREFIX)) {
            throw new JwtUnsupportedTokenException(UNSUPPORTED_TOKEN_TYPE);
        }
    }

    private void validateAccessToken(String accessToken) {
        if (jwtTokenProvider.isExpiredToken(accessToken)) {
            throw new JwtExpiredTokenException(EXPIRED_TOKEN);
        }
    }

    private void validatePayload(String email, long userId) {
        if (email == null || userId == 0) {
            throw new JwtInvalidTokenException(INVALID_TOKEN);
        }
        long userIdByEmail = userService.findUserIdByEmail(email);
        if (userId != userIdByEmail) {
            throw new JwtUnauthorizedTokenException(TOKEN_MISMATCH);
        }
    }

}
