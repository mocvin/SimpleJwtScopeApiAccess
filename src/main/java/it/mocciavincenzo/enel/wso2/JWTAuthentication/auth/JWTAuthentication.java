package it.mocciavincenzo.enel.wso2.JWTAuthentication.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthentication extends AbstractAuthenticationToken {

    private final Collection<GrantedAuthority> authorities;
    private String jwtToken;
    private String username;

    /**
     *
     * @param token
     * @param authorities
     */
    public JWTAuthentication(String username, String token, Collection<GrantedAuthority> authorities) {
        super(authorities);
        this.authorities = authorities;
        this.username = username;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserDetails getPrincipal() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public String getName() {
        return this.username;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
