package fpt.sep490.security;

import fpt.sep490.exception.FoodifyAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationDate;

    public String generateToken(Authentication authentication){
        String username = authentication.getName();

        Date currentDate = new Date();

        Date expiredDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .signWith(key())
                .compact();

        return token;
    }

    //Extract key
    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    //Get username from Token
    public String getUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = claims.getSubject();
        return username;
    }

    //Validate JWT Token
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Expired JWT Token");
        } catch (MalformedJwtException e) {
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT Token");
        } catch (SignatureException e) {
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT Signature");
        } catch (IllegalArgumentException e) {
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "JWT claims is empty");
        } catch (UnsupportedJwtException e){
            throw new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT Token");
        }
    }
}
