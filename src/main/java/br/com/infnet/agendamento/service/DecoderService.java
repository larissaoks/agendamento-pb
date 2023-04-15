package br.com.infnet.agendamento.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

@Service
public class DecoderService {

    String secret = "mySecret";

    public DecodedJWT getTokenInfo(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.require(algorithm).build().verify(token);
    }

    public Long getIdCliente(String token){
        String jwt = token.substring("Bearer".length()).trim();
        String idCliente = getTokenInfo(jwt).getSubject();
        return Long.valueOf(idCliente);
    }

    public String getTipoUser(String token) {
        String jwt = token.substring("Bearer".length()).trim();
        return getTokenInfo(jwt).getClaim("tipo_user").asString();
    }
}
