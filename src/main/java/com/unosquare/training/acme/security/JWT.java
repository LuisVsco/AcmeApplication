package com.unosquare.training.acme.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JWT {
    public static String generate(String data) {
        String SECRET_KEY = "secret";
        Integer SECOND_IN_MS = 1000;
        Integer MINUTE_IN_MS = SECOND_IN_MS * 60;
        Integer TEN_MINUTES_IN_MS = MINUTE_IN_MS * 60;

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("backendJWT")
                .setSubject(data)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TEN_MINUTES_IN_MS))
                .signWith(SignatureAlgorithm.HS512,
                        SECRET_KEY.getBytes()).compact();

        return "Bearer " + token;
    }
}
