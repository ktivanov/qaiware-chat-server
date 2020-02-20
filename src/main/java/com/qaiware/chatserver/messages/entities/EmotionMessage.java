package com.qaiware.chatserver.messages.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
public class EmotionMessage extends Message {
  public EmotionMessage(String payload) {
    super(payload);
    this.setType(MessageType.EMOTION);
  }
}
