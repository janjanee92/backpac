package com.backpac.jwt;

import com.backpac.domain.MemberRole;
import com.backpac.jwt.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    public static final String CLAIM_AUTHORITIES = "authorities";
    public static final String AUTHORITY_KEY = "authority";

    public String createToken(String username, Collection<? extends GrantedAuthority> authorities) {

        return Jwts.builder()
                .setSubject(username)
                .claim(CLAIM_AUTHORITIES, authorities)
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();
    }

    public String createToken(String username) {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(MemberRole.USER.name());

        return createToken(username, Collections.singletonList(authority));
    }

    public Authentication getAuthentication(String token) {
        Claims body = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token).getBody();

        String username = body.getSubject();
        var authorities = (List<Map<String, String>>)body.get(CLAIM_AUTHORITIES);

        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
                .map(m -> new SimpleGrantedAuthority(m.get(AUTHORITY_KEY)))
                .collect(Collectors.toSet());

        return new UsernamePasswordAuthenticationToken(username, token, simpleGrantedAuthorities);
    }
}
