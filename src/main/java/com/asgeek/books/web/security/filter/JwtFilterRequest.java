package com.asgeek.books.web.security.filter;

import com.asgeek.books.domain.auth.UserAuth;
import com.asgeek.books.domain.service.UserAuthService;
import com.asgeek.books.web.error.GenericError;
import com.asgeek.books.web.exceptions.GenericResponseException;
import com.asgeek.books.web.security.JWTGenerate;
import com.asgeek.books.web.security.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    @Autowired
    private JWTGenerate jwtGenerate;

    @Autowired
    private UserAuthService userAuthService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            String jwt = authorizationHeader.substring(7);
            String email = jwtGenerate.extractEmail(jwt);
            if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserAuth userAuth = userAuthService.getUserByEmail(email).orElse(null);
                if(jwtGenerate.validateToken(jwt, userAuth)){
                    SecurityContextHolder.getContext().setAuthentication(new TokenAuthentication(jwt, email, null));
                    request.setAttribute("claims", jwtGenerate.getClaims(jwt));
                    request.setAttribute("subject", jwtGenerate.getClaims(jwt).getSubject());
                    request.setAttribute("roles", null);
                }else
                    throw new GenericResponseException(GenericError.EXPIRED_TOKEN, HttpStatus.FORBIDDEN);
            }
        }
        filterChain.doFilter(request, response);
    }
}
