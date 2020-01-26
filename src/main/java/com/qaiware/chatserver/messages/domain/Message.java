package com.qaiware.chatserver.messages.domain;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private MessageType type;
    private String payload;
    private LocalDateTime createdAt;

    protected Message() {
        this.createdAt = LocalDateTime.now();
    }

    public Message(String payload) {
        this();
        this.payload = payload;
    }
}
