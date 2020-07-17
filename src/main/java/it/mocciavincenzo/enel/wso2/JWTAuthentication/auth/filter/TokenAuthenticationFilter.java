package it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.filter;

import it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.JWTAuthentication;
import it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.exception.JwtMissingToken;
import it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.tools.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

//@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader("X-JWT-TOKEN");
        if (Objects.isNull(jwtToken) || jwtToken.isEmpty())  {
            filterChain.doFilter(request, response);
        } else {
            //extract username from 'sub' (example: admin@carbon.super)
            String userName = jwtUtils.getUsernameFromToken(jwtToken);
            JWTAuthentication jwtAuthentication = new JWTAuthentication(userName, jwtToken, createAuthorities(jwtToken));
            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
            filterChain.doFilter(request, response);
        }
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
}
