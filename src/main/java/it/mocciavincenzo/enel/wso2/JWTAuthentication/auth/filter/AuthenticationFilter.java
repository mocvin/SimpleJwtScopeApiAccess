package it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.filter;

import it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.JWTAuthentication;
import it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.JWTAuthenticationSuccessHandler;
import it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.exception.JwtMissingToken;
import it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.tools.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * This is an authentication filter based in
 * @AbstractAuthenticationProcessingFilter workflow.
 */
@Component
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    private JWTUtils jwtUtils;

    protected AuthenticationFilter() {
        super("/scope/**");
        super.setAuthenticationSuccessHandler(new JWTAuthenticationSuccessHandler());
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String jwtToken = httpServletRequest.getHeader("X-JWT-TOKEN");
        if (Objects.isNull(jwtToken) || jwtToken.isEmpty()) throw new JwtMissingToken("X-JWT-TOKEN missing!");
        //extract username from 'sub' (example: admin@carbon.super)
        String userName = jwtUtils.getUsernameFromToken(jwtToken);
        JWTAuthentication jwtAuthentication = new JWTAuthentication(userName, jwtToken, createAuthorities(jwtToken));
        return getAuthenticationManager().authenticate(jwtAuthentication);
    }

    /**
     *
     * @param jwtToken
     * @return
     */
    private Collection<GrantedAuthority> createAuthorities(String jwtToken) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String[] scopes = jwtUtils.getScope(jwtToken);
        for (String scope: scopes) {
            authorities.add(new SimpleGrantedAuthority(scope));
        }
        return authorities;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
