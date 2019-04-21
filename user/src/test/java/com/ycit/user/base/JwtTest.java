package com.ycit.user.base;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jjwt 测试
 *
 * @author keda
 * @date 2019/3/12
 */
public class JwtTest {

    @Test
    public void quickStart() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jws = Jwts.builder()
                .setSubject("Joe")
                .signWith(key)
                .compact();
        System.out.println(jws);
        assert Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody().getSubject().equals("Joe");
        assert true:"true";
    }

//    @Test
    public void custom() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", "xlch");
        claims.put("created", new Date());
        String jwt = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date())
                .signWith(SignatureAlgorithm.HS512, "cus")
                .compact();
    }


    public void detail() {
        String header = "{\"alg\":\"HS256\"}";
        String body = "{\"sub\":\"Joe\"}";
    }

}
