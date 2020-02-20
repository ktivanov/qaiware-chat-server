package com.qaiware.chatserver.messages.factories;

import com.qaiware.chatserver.messages.domain.EmotionMessage;
import org.springframework.stereotype.Component;

@Component("emotionMessageFactory")
public class EmotionMessageFactory implements MessageFactory<EmotionMessage> {

  @Override
  public EmotionMessage createMessage(String payload) {
    return new EmotionMessage(payload);
  }
}
