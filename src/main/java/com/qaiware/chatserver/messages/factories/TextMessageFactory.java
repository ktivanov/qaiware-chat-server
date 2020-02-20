package com.qaiware.chatserver.messages.factories;

import com.qaiware.chatserver.messages.domain.TextMessage;
import org.springframework.stereotype.Component;

@Component("textMessageFactory")
public class TextMessageFactory implements MessageFactory<TextMessage> {

  @Override
  public TextMessage createMessage(String payload) {
    return new TextMessage(payload);
  }
}
