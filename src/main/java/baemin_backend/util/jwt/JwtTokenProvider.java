package baemin_backend.util.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${secret.jwt-secret-key}")
    private static String JWT_SECRET_KEY;

    @Value("${secret.jwt-expired-in}")
    private static long JWT_EXPIRED_IN;

    public static String createToken(long userId) {
        log.info("JWT key={}", JWT_SECRET_KEY);

        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
        Date now = new Date();
        Date validity = new Date(now.getTime() + JWT_EXPIRED_IN);
        SecretKey secretKey = Keys.hmacShaKeyFor(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .claim("userId", userId)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

}
