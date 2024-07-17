package com.mandiri.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mandiri.entity.AppUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {


    private String jwtSecret = "bismillahberkaj";
    private String appName = "Mandiri Loan";
    private long jwtExp = 3600;

    private Algorithm algo;

    @PostConstruct
    private void getAlgorithm() {
        this.algo = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));

    }

    public String generateToken(AppUser appUser){
        return JWT.create()
                .withIssuer(appName)
                .withSubject(appUser.getId())
                .withExpiresAt(Instant.now().plusSeconds(jwtExp))
                .withIssuedAt(Instant.now())
                .withClaim("Role", appUser.getRole().name())
                .sign(algo);
    }

    public boolean verifyJwtToken(String token){
        try {
            JWTVerifier verifier = JWT.require(algo).build();
            DecodedJWT decodeJWT = verifier.verify(token);
            return decodeJWT.getIssuer().equals(appName);
        } catch (JWTVerificationException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> getUserInfoByToken(String token){
        try {
            JWTVerifier verifier = JWT.require(algo).build();
            DecodedJWT decodeJWT = verifier.verify(token);
            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("userId", decodeJWT.getSubject());
            userInfo.put("role", decodeJWT.getClaim("Role").asString());
            return userInfo;
        } catch (JWTVerificationException e) {
            throw new RuntimeException(e);
        }
    }

}

