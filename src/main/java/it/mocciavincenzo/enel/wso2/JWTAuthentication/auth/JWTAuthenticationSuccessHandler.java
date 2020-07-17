package it.mocciavincenzo.enel.wso2.JWTAuthentication.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final static Logger LOG = LoggerFactory.getLogger(JWTAuthenticationSuccessHandler.class.getName());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //nothing to do
        LOG.info("Requested URL {} with Verb {}", request.getRequestURI(), request.getMethod());
        LOG.info("User {} is authenticated", authentication.getName());
    }
}
