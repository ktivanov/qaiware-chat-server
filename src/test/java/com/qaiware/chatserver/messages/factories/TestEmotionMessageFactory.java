package com.qaiware.chatserver.messages.factories;

import static org.junit.Assert.assertNotNull;

import com.qaiware.chatserver.messages.entities.EmotionMessage;
import org.junit.Test;

public class TestEmotionMessageFactory {
  private MessageFactory<EmotionMessage> emotionMessageFactory = new EmotionMessageFactory();

  @Test
  public void testEmotionMessageCreation() {
    EmotionMessage emotionMessage = emotionMessageFactory.createMessage("test");
    assertNotNull(emotionMessage);
  }
}
