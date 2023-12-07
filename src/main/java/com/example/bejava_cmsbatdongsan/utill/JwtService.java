package com.example.bejava_cmsbatdongsan.utill;

import com.example.bejava_cmsbatdongsan.model.UserCustomDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.PushBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "p7lrcccy87yemz2czal347tyhi3lod7m7dldt5fgeluirod9oplwdur8j3hf4l4d";
    private static final int expireInMs = 6000 * 1000;

    public String extractUsername(String token) {
        return extractClam(token, Claims::getSubject);
    }
    public <T> T extractClam(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClams(token);
        return claimsTFunction.apply(claims);
    }

    public Claims extractAllClams(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public String generateToken(UserCustomDetail userCustomDetail) {
        return generateToken(new HashMap<>(), userCustomDetail);
    }
    public String generateToken(Map<String, Object> extractClam, UserCustomDetail userCustomDetail) {
        return buildToken(extractClam, userCustomDetail);
    }

    public String buildToken(Map<String, Object> extractClam, UserCustomDetail userCustomDetail) {
        return Jwts
                .builder()
                .setClaims(extractClam)
                .claim("roleName", userCustomDetail.getNguoiDung().getRole().getTenQuyenHan())
                .setSubject(userCustomDetail.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireInMs))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClam(token, Claims::getExpiration);
    }

    public Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
