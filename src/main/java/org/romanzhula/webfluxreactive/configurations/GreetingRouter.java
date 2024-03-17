package org.romanzhula.webfluxreactive.configurations;

import org.romanzhula.webfluxreactive.handlers.Greeting;
import org.romanzhula.webfluxreactive.handlers.GreetingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

        RequestPredicate route =
                GET("/hello")
                .and(accept(MediaType.APPLICATION_JSON));

        return RouterFunctions
                .route(route, greetingHandler::hello)
                .andRoute(
                     RequestPredicates.GET("/"),
                     serverRequest -> {
                         return ServerResponse
                                 .ok().contentType(MediaType.TEXT_PLAIN)
                                 .body(
                                         BodyInserters.fromValue("Our Main Page")
                                 );
                     }
                );
    }
}
