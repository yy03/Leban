package com.yy.yeb.config;


import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtils {

     private static final String CLAIM_KEY_USERNAME = "sub";
     private static final String CLAIM_KEY_CREATED = "created";
     @Value("${jwt.secret}")
     private String secret;
     @Value("${jwt.expiration}")
     private long expiration;

//     根据用户信息生成token
     public String generateToken(UserDetails userDetails) {
         Map<String, Object> claims = new HashMap<>();
         claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
         claims.put(CLAIM_KEY_CREATED, new Date());
         return generateToken(claims);
     }

//     从token中获取登录用户名
     public String getUsernameFormToken(String token) {
         String username;
         try {
             Claims claims = getClaimsFromToken(token);
             username = claims.getSubject();
             System.out.println(username);
         } catch (Exception e) {
             username = null;
         }
         return username;
     }

//      验证token是否有效
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFormToken(token);
        return username.equals(userDetails.getUsername()) && !isExpired(token);
    }

//    判断token能否被刷新
    public boolean canRefresh(String token) {
         return !isExpired(token);
    }

//    刷新token
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }


//    判断token是否失效
    private boolean isExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expDate = claims.getExpiration();
        return expDate.before(new Date());
    }


    //      从token中获取荷载
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    //     根据荷载生成token
    private String generateToken(Map<String, Object> claims) {
         return Jwts.builder()
                 .setClaims(claims)
                 .setExpiration(generateExpirationDate())
                 .signWith(SignatureAlgorithm.HS512, secret)
                 .compact();

    }
//      生成token失效时间
    private Date generateExpirationDate() {
         return new Date(System.currentTimeMillis() + expiration * 1000);
    }

}
