package com.qaiware.chatserver.messages.factories;

import static org.junit.Assert.assertNotNull;

import com.qaiware.chatserver.messages.entities.TextMessage;
import org.junit.Test;

public class TestTextMessageFactory {
  private MessageFactory<TextMessage> textMessageMessageFactory = new TextMessageFactory();

  @Test
  public void testTextMessageCreation() {
    TextMessage textMessage = textMessageMessageFactory.createMessage("test");
    assertNotNull(textMessage);
  }
}
