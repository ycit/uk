package com.ycit.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * jwt util
 * JWT 的三个部分依次如下:
 * Header（头部）
 * Payload（负载）
 * Signature（签名）
 *生成的JWT 为 ：Header.Payload.Signature
 * Header
 * {
 *   "alg": "HS256",
 *   "typ": "JWT"
 * }
 *
 * payload
 * iss (issuer)：签发人
 * exp (expiration time)：过期时间
 * sub (subject)：主题
 * aud (audience)：受众
 * nbf (Not Before)：生效时间
 * iat (Issued At)：签发时间
 * jti (JWT ID)：编号
 *
 * Signature
 * 生成规则：
 * HMACSHA256(
 *   base64UrlEncode(header) + "." +
 *   base64UrlEncode(payload),
 *   secret)
 *
 * @author uk
 * 2019/3/31 17:06
 */
@Slf4j
public class JwtUtil {

    public static String generateJWT(String subject) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Constant.TOKEN_SIGN_KEY);

        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        return Jwts.builder()
                .setId("id")
                //签发时间
                .setIssuedAt(new Date())
                //主题
                .setSubject(subject)
                //签发人
                .setIssuer("http://www.uk.com")
                //有效期
                .setExpiration(new Date(System.currentTimeMillis() + Constant.TOKEN_EXPIRATION_MS))
                //签名
                .signWith(signingKey, signatureAlgorithm)
                .compact();
    }

    public static void parseJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(Constant.TOKEN_SIGN_KEY))
                .parseClaimsJws(jwt).getBody();
        log.debug("parse result is {}", claims.toString());
    }

    public static String getUsernameByToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(Constant.TOKEN_SIGN_KEY))
                .parseClaimsJws(token).getBody();
        log.debug("parse result is {}", claims.toString());
        return claims.getSubject();
    }

    private static String generateApiKey() {
        Key signKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        return Encoders.BASE64.encode(signKey.getEncoded());
    }


    public static void main(String[] args) throws Exception {
        String apiKey =  generateApiKey();
        System.out.println("base 64 is : " + apiKey);
        Thread.sleep(1000);
        String jwt = generateJWT("xlch");
        System.out.println("jwt is:  " + jwt);
        parseJWT(jwt);
    }

}
