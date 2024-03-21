package org.romanzhula.webfluxreactive.controllers;

import lombok.RequiredArgsConstructor;
import org.romanzhula.webfluxreactive.configurations.JWTUtil;
import org.romanzhula.webfluxreactive.models.User;
import org.romanzhula.webfluxreactive.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
//@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final static ResponseEntity<Object> UNAUTHORIZED = ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); //singletone

    @PostMapping("/login")
    public Mono<ResponseEntity> login(
            ServerWebExchange serverWebExchange
    ) {
        return serverWebExchange.getFormData().flatMap(credentials ->
                   userService.findByUsername(credentials.getFirst("username"))
                           .cast(User.class)
                           .map(userDetails ->
                                    Objects.equals(credentials.getFirst("password"), userDetails.getPassword()
                                )
                                ? ResponseEntity.ok(jwtUtil.generateToken(userDetails))
                                            : UNAUTHORIZED
                           )
                   .defaultIfEmpty(UNAUTHORIZED)
        );
    }
}
