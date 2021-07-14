package com.backpac.jwt.filter;

import com.backpac.jwt.TokenProvider;
import com.backpac.jwt.config.JwtConfig;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

        if (!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");

        try {

            SecurityContextHolder.getContext().setAuthentication(tokenProvider.getAuthentication(token));
        } catch (JwtException e) {
            throw new IllegalStateException(("신뢰할 수 없는 토큰입니다."));
        }

        filterChain.doFilter(request, response);

    }


}
