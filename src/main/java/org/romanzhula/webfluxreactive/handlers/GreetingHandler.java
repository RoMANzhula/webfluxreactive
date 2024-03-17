package org.romanzhula.webfluxreactive.handlers;

import org.romanzhula.webfluxreactive.models.Message;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class GreetingHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        Long start = request.queryParam("start")
                .map(Long::valueOf)
                .orElse(0L);
        Long count = request.queryParam("count")
                .map(Long::valueOf)
                .orElse(4L);

        Flux<Message> data = Flux
                .just(
                        "Hello, Flux reactive!",
                        "Hello second!",
                        "Hello third",
                        "Hello fourth",
                        "Hello fifth"
                )
                .skip(start) //from we start
                .take(count) //times using
                .map(Message::new);

        return ServerResponse
                .ok().contentType(MediaType.APPLICATION_JSON)
                .body(data, Message.class);
    }

    public Mono<ServerResponse> index(ServerRequest serverRequest) {
        String user = serverRequest.queryParam("user")
                .orElse("Nobody");
        return ServerResponse
                .ok()
                .render("index", Map.of("user", user));
    }

}
