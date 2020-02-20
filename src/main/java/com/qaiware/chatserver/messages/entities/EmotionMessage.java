package com.qaiware.chatserver.messages.domain;

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
