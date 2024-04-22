package com.example.demo.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;

//En esta clase generamos los servicios de jwt
@Service
public class TokenService {

    @Value("{api.security.secret}")
    private String apiSecret;

    public String generateToken(User user)
    {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("cineplanet")
                    .withSubject(user.getUsername())
                    .withClaim("user_id",user.getUser_id())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        }catch (JWTCreationException exception)
        {
            throw  new RuntimeException();
        }


    }
    public String getSubject(String token)
    {

        if(token == null)
        {
            throw new RuntimeException();
        }

        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            verifier = JWT.require(algorithm)
                    .withIssuer("cineplanet")
                    .build()
                    .verify(token);
            verifier.getSubject();
        }catch (JWTVerificationException exception)
        {
            System.out.println(exception.toString());
        }
        if(verifier.getSubject() == null)
        {
            throw new RuntimeException("Invalid verifier");
        }
        return verifier.getSubject();
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }


    //este metodo sirve para obtener el usuario y id del token del usuario
    public String[] decodeToken(String JWTtoken)
    {
        String[] userDecode = new String[2];

        String[] parts = JWTtoken.split("\\."); //

        Base64.Decoder decoder = Base64.getUrlDecoder();

        String payloadJson = new String(decoder.decode(parts[1]));


        String[] bodyParts = payloadJson.replace('}',' ').trim().split(",");

        userDecode[0]= returnUsername(bodyParts[3]);
        userDecode[1]= returnUserId(bodyParts[1]);

        return userDecode;
    }
    public String returnUsername(String correo)
    {
        String[] username = correo.split(":");
        return username[1];
    }
    public String returnUserId(String id)
    {
        String[] userId = id.split(":");
        return userId[1];
    }

}
