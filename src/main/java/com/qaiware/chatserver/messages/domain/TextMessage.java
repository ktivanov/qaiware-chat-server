package com.qaiware.chatserver.messages.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class TextMessage extends Message{
    public TextMessage(String payload) {
        super(payload);
        this.setType(MessageType.TEXT);
    }
}
