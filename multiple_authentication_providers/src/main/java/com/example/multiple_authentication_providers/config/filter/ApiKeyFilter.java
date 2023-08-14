package com.example.multiple_authentication_providers.config.filter;

import com.example.multiple_authentication_providers.config.authentication.ApiKeyAuthentication;
import com.example.multiple_authentication_providers.config.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {
    private final String key;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CustomAuthenticationManager manager = new CustomAuthenticationManager(key);
        var requestKey = request.getHeader("x-api-key");
        if ("null".equals(requestKey)|| requestKey == null){
            filterChain.doFilter(request,response);
        }
        var auth = new ApiKeyAuthentication(requestKey);
        try {
            var a = manager.authenticate(auth);
            if(a.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(a);
                filterChain.doFilter(request,response);
            }else{
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }catch (AuthenticationException e){
             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}