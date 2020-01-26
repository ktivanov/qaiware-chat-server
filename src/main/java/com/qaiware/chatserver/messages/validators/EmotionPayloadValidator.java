package com.qaiware.chatserver.messages.validators;

import org.springframework.stereotype.Component;

@Component("emotionPayloadValidator")
public class EmotionPayloadValidator implements PayloadValidator {
    private static final String CONTAINS_DIGIT_PATTERN = ".*\\d.*";

    @Override
    public boolean isPayloadValid(String payload)  {
        return payload.length() >= 2 && payload.length() <= 10 && !payload.matches(CONTAINS_DIGIT_PATTERN);
    }
}
