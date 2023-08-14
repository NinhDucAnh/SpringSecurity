package com.example.custom_authentication.config.security.provider;

import com.example.custom_authentication.config.security.authentication.CustomAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider  implements AuthenticationProvider {
    @Value("{secret.key}")
    private String key;
    @Override
    public Authentication  authenticate(Authentication authentication) throws AuthenticationException {
       CustomAuthentication ca = (CustomAuthentication) authentication;

       String keyHeader = ca.getKey();
       if(key.equals(keyHeader)){
           CustomAuthentication result = new CustomAuthentication(true,null);
           return result;
       }
       throw new BadCredentialsException("Oh no");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
