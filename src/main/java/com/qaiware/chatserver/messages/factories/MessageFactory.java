package com.qaiware.chatserver.messages.factories;

import com.qaiware.chatserver.messages.entities.Message;

public interface MessageFactory<T extends Message> {
  T createMessage(String payload);
}
