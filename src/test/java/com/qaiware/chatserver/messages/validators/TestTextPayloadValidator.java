package com.qaiware.chatserver.messages.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTextPayloadValidator {
  TextPayloadValidator payloadValidator = new TextPayloadValidator();

  @Test
  public void testValidTextMessage() {
    String payload = "Simple Test";
    Assertions.assertTrue(payloadValidator.isPayloadValid(payload));
  }

  @Test
  public void testTooSmallTextMessage() {
    String payload = "";
    Assertions.assertFalse(payloadValidator.isPayloadValid(payload));
  }

  @Test
  public void testTooBigTextMessage() {
    String payload = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttes";
    payload += "ttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttestt";
    Assertions.assertTrue(payload.length() > 160);
    Assertions.assertFalse(payloadValidator.isPayloadValid(payload));
  }

  @Test
  public void testUpperCornerCaseTextMessage() {
    String payload = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
    payload += "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
    Assertions.assertEquals(160, payload.length());
    Assertions.assertTrue(payloadValidator.isPayloadValid(payload));
  }

  @Test
  public void testLowerCornerCaseTextMessage() {
    String payload = "a";
    Assertions.assertTrue(payload.length() == 1);
    Assertions.assertTrue(payloadValidator.isPayloadValid(payload));
  }
}
