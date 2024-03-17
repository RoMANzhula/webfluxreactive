package org.romanzhula.webfluxreactive.repositories;

import org.romanzhula.webfluxreactive.models.Message;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MessageRepository extends ReactiveCrudRepository<Message, Long> {

}
