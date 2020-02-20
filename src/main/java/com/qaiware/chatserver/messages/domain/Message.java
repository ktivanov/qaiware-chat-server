package com.qaiware.chatserver.messages.domain;

import java.time.LocalDateTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;

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
