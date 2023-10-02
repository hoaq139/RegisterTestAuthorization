package com.example.registertestauthorization.util;

import com.example.registertestauthorization.entity.Account;
import com.example.registertestauthorization.model.TokenPayLoad;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    @Value("hdgasidbaoisbdosabdasodjasdnonasjndnasondnasdnandansdnausbdbashdbabshdbuashi")
    private String secret;
    public String generateToken(Account account,long expiredDate){
        Map<String, Object> claims = new HashMap<>();
        TokenPayLoad tokenPayLoad = TokenPayLoad.builder()
                .userId(account.getAccountId())
                .email(account.getEmail())
                .build();
        claims.put("payload", tokenPayLoad);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredDate*1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
    public TokenPayLoad getTokenPayload(String token){
        return getClaimsFromToken(token, (Claims claim) -> {
            Map<String, Object> mapResult = (Map<String, Object>) claim.get("payload");
            return TokenPayLoad.builder()
                    .userId((String) mapResult.get("accountId"))
                    .email((String) mapResult.get("email")).build();
        });
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claimResolver.apply(claims);

    }
    public boolean validate(String token, Account account) {
        TokenPayLoad tokenPayload = getTokenPayload(token);

        return tokenPayload.getUserId().equals(account.getAccountId()) && tokenPayload.getEmail().equals(account.getEmail())
                && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiredDate = getClaimsFromToken(token, Claims::getExpiration);
        return expiredDate.before(new Date());
    }
}
