package com.ycit.user.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

/**
 * jwt util
 *
 * @author uk
 * 2019/3/31 17:06
 */
public class JwtUtil {

    public String generateToken(String subject) {
        Claims claims = Jwts.claims().setSubject(subject);
//        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        return Jwts.builder().setClaims(claims)
                .setIssuer("http://www.ycit.com")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Constants.TOKEN_EXPIRATION_SECOND))
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .compact();
    }

}
