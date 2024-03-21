package org.romanzhula.webfluxreactive.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "messages")
public class Message {
    @Id
    private Long id;

    private String data;

    public Message(String data) {
        this.data = data;
    }

}
