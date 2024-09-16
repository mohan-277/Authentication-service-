package com.sbear.org.authenticationservice;

import com.sbear.org.authenticationservice.config.UserInfoUserDetails;
import com.sbear.org.authenticationservice.entity.UserInfo;
import com.sbear.org.authenticationservice.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JwtServiceTest {
    private final JwtService jwtService = new JwtService();

    @Test
    public void testGenerateToken() {
        String username = "john.doe";
        String token = jwtService.generateToken(username);

        assertTrue(token.startsWith("eyJ"));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtService.getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        assertEquals(username, claims.getSubject());
    }

    @Test
    public void testValidateToken() {
        String username = "john.doe";
        String token = jwtService.generateToken(username);

        UserDetails userDetails = new UserInfoUserDetails(new UserInfo(username, "password", "USER"));

        assertTrue(jwtService.validateToken(token, userDetails));
    }
}
