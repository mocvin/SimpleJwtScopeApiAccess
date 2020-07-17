package it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.tools;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.function.Function;

/**
 *
 */
@Component
public class JWTUtils {

    @Value("${keystore.alias}")
    private String alias;

    @Autowired
    private KeysManagement keysManagement;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
          return Jwts
                  .parser()
                  .setSigningKey(keysManagement.ketPublicKey(alias))
                  .parseClaimsJws(token)
                  .getBody();
    }

    public String[] getScope(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        String scope = claims.get("scope", String.class);
        if (Objects.nonNull(scope))
            return scope.split(" ");
        return new String[0];

    }
    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
