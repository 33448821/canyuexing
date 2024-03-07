package com.example.OrderFood.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 永恒星辰
 * Date 2024/2/29 19:27
 * Description
 */
public class JwtUtils {
    private static final String secretKey = "123456789";
    private static final long expirationMillis = 3600000 * 24L; // 3600000ms = 1 h

    /**
     * Generate a JWT token with the provided claims.
     *
     * @param claims Map containing payload claims
     * @return JWT token as a string
     */
    public static String generateJwtToken(Map<String, Object> claims) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationMillis);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTCreator.Builder builder = JWT.create()
                .withIssuedAt(now)
                .withExpiresAt(expirationDate);

        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            builder.withClaim(entry.getKey(), entry.getValue().toString());
        }

        return builder.sign(algorithm);
    }

    /**
     * Parse a JWT token and return its claims.
     *
     * @param jwtToken JWT token as a string
     * @return Map of claims extracted from the token
     */
    public static Map<String, Claim> parseJwtToken(String jwtToken) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(jwtToken);
            return decodedJWT.getClaims();
        } catch (JWTVerificationException exception) {
            // Token 验证失败，这里可以根据实际需求进行异常处理
            return null;
        }
    }

    public static void main(String[] args) {
        // 生成jwt
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", "星辰");
        claims.put("phone", "13123456789");

        String jwtToken = generateJwtToken(claims);
        System.out.println("Generated JWT Token: " + jwtToken);


        // 解析jwt
        Map<String, Claim> parsedClaims = parseJwtToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwaG9uZSI6IjEzMTIzNDU2Nzg5IiwibmFtZSI6IuaYn-i-sCIsImV4cCI6MTcwOTI5MjY4NSwiaWF0IjoxNzA5MjA2Mjg1fQ.sR7aG2ieQgbu3tfx9fWtLn5UXbPVaThBBvXSaNh7cNE");

        System.out.println("Parsed JWT Claims:");

        //因为new Date()参数为毫秒，所以将时间戳*1000，时间戳单位是秒
        System.out.println(new Date(parsedClaims.get("exp").asLong() * 1000));
        System.out.println("User: " + parsedClaims.get("name"));
    }
}