package com.qaiware.chatserver.messages.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class TextMessage extends Message {
  public TextMessage(String payload) {
    super(payload);
    this.setType(MessageType.TEXT);
  }
}
