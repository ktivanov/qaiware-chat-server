package com.qaiware.chatserver.messages.validators;

import org.springframework.stereotype.Component;

@Component("textPayloadValidator")
public class TextPayloadValidator implements PayloadValidator {

  @Override
  public boolean isPayloadValid(String payload) {
    return payload.length() > 0 && payload.length() <= 160;
  }
}
