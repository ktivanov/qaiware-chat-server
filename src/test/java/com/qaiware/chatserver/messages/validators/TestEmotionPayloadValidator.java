package com.qaiware.chatserver.messages.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestEmotionPayloadValidator {
  EmotionPayloadValidator payloadValidator = new EmotionPayloadValidator();

  @Test
  public void testValidPayload() {
    String payload = "happy";
    Assertions.assertTrue(payloadValidator.isPayloadValid(payload));
  }

  @Test
  public void testInvalidEmotionContainingDigit() {
    String payload = "happy4";
    Assertions.assertFalse(payloadValidator.isPayloadValid(payload));

    payload = "smile4happy";
    Assertions.assertFalse(payloadValidator.isPayloadValid(payload));
  }

  @Test
  public void testInvalidTooSmallPayload() {
    String payload = "h";
    Assertions.assertFalse(payloadValidator.isPayloadValid(payload));

    payload = "";
    Assertions.assertFalse(payloadValidator.isPayloadValid(payload));
  }

  @Test
  public void testInvalidTooBigPayload() {
    String payload = "smilesmiles";
    Assertions.assertTrue(payload.length() > 10);
    Assertions.assertFalse(payloadValidator.isPayloadValid(payload));
  }

  @Test
  public void testUpperCornerCasePayloadSize() {
    String payload = "smilesmile";
    Assertions.assertEquals(10, payload.length());
    Assertions.assertTrue(payloadValidator.isPayloadValid(payload));
  }

  @Test
  public void testLowerCornerCasePayloadSize() {
    String payload = "sm";
    Assertions.assertEquals(2, payload.length());
    Assertions.assertTrue(payloadValidator.isPayloadValid(payload));
  }
}
