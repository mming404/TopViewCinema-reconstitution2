package com.ysm.www.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JWT工具
 *
 * @author Albumen
 */
@Component
public class JwtUtil {

    private static Algorithm algorithm;

    public JwtUtil() {
//        RSAPublicKey publicKey = RsaKeyUtil.getInstance().getPublicKey();
//        RSAPrivateKey privateKey = RsaKeyUtil.getInstance().getPrivateKey();
//        algorithm = Algorithm.RSA256(publicKey, privateKey);
        algorithm = Algorithm.HMAC256("TopViewYsm@666");
    }

    public String create(String subject, long expire) {
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer("Topview")
                    .withSubject(subject)
                    .withExpiresAt(new Date(System.currentTimeMillis() + expire))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            return null;
        }
        return token;
    }

    public String getSubject(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("Topview")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
}
