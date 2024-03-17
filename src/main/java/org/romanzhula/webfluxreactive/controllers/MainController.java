package org.romanzhula.webfluxreactive.controllers;

import org.romanzhula.webfluxreactive.models.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/controller")
public class MainController {

    @GetMapping
    public Flux<Message> list(
            @RequestParam(defaultValue = "0") Long start,
            @RequestParam(defaultValue = "4") Long count
    ) {
        return Flux
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
    }
}
