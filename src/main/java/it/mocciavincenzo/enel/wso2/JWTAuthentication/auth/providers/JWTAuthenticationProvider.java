package it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.providers;

import it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.JWTAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication jwtAuthentication = authentication;
        //The JWT was arleady verified in previous stage and the user authenticated
        return jwtAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JWTAuthentication.class.isAssignableFrom(authentication));
    }

}
