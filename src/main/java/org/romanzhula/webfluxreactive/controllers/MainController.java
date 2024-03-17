package org.romanzhula.webfluxreactive.controllers;

import lombok.RequiredArgsConstructor;
import org.romanzhula.webfluxreactive.models.Message;
import org.romanzhula.webfluxreactive.services.MessageService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/controller")
@RequiredArgsConstructor
public class MainController {
    private final MessageService messageService;

    @GetMapping
    public Flux<Message> list(
            @RequestParam(defaultValue = "0") Long start,
            @RequestParam(defaultValue = "4") Long count
    ) {
        return messageService.list();
    }

    @PostMapping
    public Mono<Message> add(
            @RequestBody Message message
    ) {
        return messageService.saveMessage(message);
    }
}
