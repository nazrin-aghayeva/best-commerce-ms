package org.listing.security.jwt;

import io.jsonwebtoken.*;
import org.listing.security.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils{
    private static final Logger logger= LoggerFactory.getLogger(JwtUtils.class);

    @Value("${sign-up-ms.app.jwtSecret}")
    private String jwtSecret;

    @Value("${sign-up-ms.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateToken(Authentication authentication){
        UserDetailsImpl userPrincipal= (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder().
                setSubject(String.valueOf(userPrincipal.getId())).
                setIssuedAt(new Date()).
                setExpiration(new Date(new Date().getTime()+ jwtExpirationMs)).
                signWith(SignatureAlgorithm.HS512, jwtSecret).
                compact();
    }

    public Integer getUserIdFromJwt(String token){
        return Integer.parseInt(Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject());
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            logger.error("Invalid JWT signature: {}", ex.getMessage());
        }
        catch (MalformedJwtException ex){
            logger.error("Invalid JWT token: {}", ex.getMessage());
        }
        catch (ExpiredJwtException ex){
            logger.error("JWT token is expired: {}", ex.getMessage());
        }
        catch (UnsupportedJwtException ex){
            logger.error("JWT token is unsupported: {}", ex.getMessage());
        }
        catch (IllegalArgumentException ex){
            logger.error("JWT claims string is empty: {}", ex.getMessage());
        }
        return false;
    }
}

