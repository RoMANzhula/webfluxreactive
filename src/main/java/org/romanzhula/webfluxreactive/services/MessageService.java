package org.romanzhula.webfluxreactive.services;

import lombok.RequiredArgsConstructor;
import org.romanzhula.webfluxreactive.models.Message;
import org.romanzhula.webfluxreactive.repositories.MessageRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public Flux<Message> list() {
        return messageRepository.findAll();
    }

    public Mono<Message> saveMessage(Message message) {
        return messageRepository.save(message);
    }
}
