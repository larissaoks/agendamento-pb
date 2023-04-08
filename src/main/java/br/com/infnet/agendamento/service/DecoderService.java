package br.com.infnet.agendamento.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

@Service
public class DecoderService {

    String secret = "mySecret";

    public String getTokenInfo(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        DecodedJWT jwt = JWT.require(algorithm).build().verify(token);

        return jwt.getSubject();
    }

    public Long getIdCliente(String token){
        String jwt = token.substring("Bearer".length()).trim();
        String infoToken = getTokenInfo(jwt);
        return Long.valueOf(infoToken);
    }
}
