package com.qaiware.chatserver.messages.validators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestEmotionPayloadValidator {
    EmotionPayloadValidator payloadValidator = new EmotionPayloadValidator();

    @Test
    public void testValidPayload(){
        String payload = "happy";
        assertTrue(payloadValidator.isPayloadValid(payload));
    }

    @Test
    public void testInvalidEmotionContainingDigit(){
        String payload = "happy4";
        assertFalse(payloadValidator.isPayloadValid(payload));

        payload = "smile4happy";
        assertFalse(payloadValidator.isPayloadValid(payload));
    }

    @Test
    public void testInvalidTooSmallPayload(){
        String payload = "h";
        assertFalse(payloadValidator.isPayloadValid(payload));

        payload = "";
        assertFalse(payloadValidator.isPayloadValid(payload));
    }

    @Test
    public void testInvalidTooBigPayload(){
        String payload = "smilesmiles";
        assertTrue(payload.length() > 10);
        assertFalse(payloadValidator.isPayloadValid(payload));
    }

    @Test
    public void testUpperCornerCasePayloadSize(){
        String payload = "smilesmile";
        assertEquals(10, payload.length());
        assertTrue(payloadValidator.isPayloadValid(payload));
    }

    @Test
    public void testLowerCornerCasePayloadSize(){
        String payload = "sm";
        assertEquals(2, payload.length());
        assertTrue(payloadValidator.isPayloadValid(payload));
    }
}
