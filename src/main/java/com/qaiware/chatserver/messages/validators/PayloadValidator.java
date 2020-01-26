package com.qaiware.chatserver.messages.validators;

public interface PayloadValidator {
    /**
     * Checks if payload is valid.
     * @param payload - that is going to be validated
     * @return Return true if it is valid, otherwise return false.
     */
    boolean isPayloadValid(String payload);
}
