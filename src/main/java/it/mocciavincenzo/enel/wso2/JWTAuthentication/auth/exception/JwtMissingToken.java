package it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.exception;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class JwtMissingToken extends AuthenticationException {
    public JwtMissingToken(String s) {
        super(s);
    }
}
