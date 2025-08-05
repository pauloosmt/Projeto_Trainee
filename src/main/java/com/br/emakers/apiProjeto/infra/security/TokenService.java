package com.br.emakers.apiProjeto.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.br.emakers.apiProjeto.data.entity.Pessoa;



@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    //Geraçao do token JWT
    public String gerarToken(Pessoa pessoa) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                            .withIssuer("apiProjeto")
                            .withSubject(pessoa.getEmail())
                            .withExpiresAt(genExpriratedDate())
                            .sign(algorithm);
            return token;
                        } catch(JWTCreationException exception) {
                            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    //Validaçao de token JWT
    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("apiProjeto")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    //Tempo de expiraçao do token
    private Instant genExpriratedDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));//Horario Brasilia
    }
}
