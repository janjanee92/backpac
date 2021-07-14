package com.backpac.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
@RequiredArgsConstructor
public class JwtSecretKey {

    private final JwtConfig jwtConfig;

    @Bean
    public SecretKey secretKey() {
        String secretKey = jwtConfig.getSecretKey();
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
