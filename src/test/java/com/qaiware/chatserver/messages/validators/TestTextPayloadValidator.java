package com.qaiware.chatserver.messages.validators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTextPayloadValidator {
    TextPayloadValidator payloadValidator = new TextPayloadValidator();

    @Test
    public void testValidTextMessage(){
        String payload = "Simple Test";
        assertTrue(payloadValidator.isPayloadValid(payload));
    }

    @Test
    public void testTooSmallTextMessage(){
        String payload = "";
        assertFalse(payloadValidator.isPayloadValid(payload));
    }

    @Test
    public void testTooBigTextMessage(){
        String payload = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttestt";
        assertTrue(payload.length() > 160);
        assertFalse(payloadValidator.isPayloadValid(payload));
    }

    @Test
    public void testUpperCornerCaseTextMessage(){
        String payload = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
        assertEquals(160, payload.length());
        assertTrue(payloadValidator.isPayloadValid(payload));
    }

    @Test
    public void testLowerCornerCaseTextMessage(){
        String payload = "a";
        assertTrue(payload.length() == 1);
        assertTrue(payloadValidator.isPayloadValid(payload));
    }
}
