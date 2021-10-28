package com.iamnbty.training.backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.iamnbty.training.backend.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {

    @Value("${app.token.secret}")
    private String secret;

    @Value("${app.token.issuer}")
    private String issuer;

    public String tokenize(User user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);
        Date expiresAt = calendar.getTime();

        return JWT.create()
                .withIssuer(issuer)
                .withClaim("principal", user.getId()) //we can insert another information in token such as ID
                .withClaim("role", "USER") //who is me such as admin or user
                .withExpiresAt(expiresAt) //when end token
                .sign(algorithm());
    }


    //Check and validate Token is correct or not
    public DecodedJWT verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm())
                    .withIssuer(issuer)
                    .build();
            //if can verifier token return not null
            return verifier.verify(token);

        } catch (Exception e) {
            //if can't verifier token return null
            return null;
        }
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
        //Algorithm to decode JWI
    }

}
