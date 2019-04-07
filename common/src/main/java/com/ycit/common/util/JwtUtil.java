package com.ycit.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * jwt util
 *
 * @author uk
 * 2019/3/31 17:06
 */
public class JwtUtil {

    public static String generateToken(String subject)throws Exception {
        Claims claims = Jwts.claims().setSubject(subject);
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodes = encoder.encode(Constant.TOKEN_SIGN_KEY.getBytes("UTF-8"));
        byte[] byteKey = Base64.getDecoder().decode(encodes);
        X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://www.ycit.com") //签发人
                .setIssuedAt(new Date()) //签发时间
                .setExpiration(new Date(System.currentTimeMillis() + Constant.TOKEN_EXPIRATION_SECOND)) //有效期
                .signWith(secretKeySpec) //签名
                .compact();
    }



    public static void main(String[]args)throws Exception {
        System.out.println(generateToken("xlch"));
        System.out.println(generateToken("ssx"));
        Key signKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String base64 = Encoders.BASE64.encode(signKey.getEncoded());
        System.out.println(base64);
    }

}
