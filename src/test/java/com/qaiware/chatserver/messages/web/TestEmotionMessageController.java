package com.qaiware.chatserver.messages.web;

import com.qaiware.chatserver.messages.domain.MessageRepository;
import com.qaiware.chatserver.messages.factories.EmotionMessageFactory;
import com.qaiware.chatserver.messages.validators.EmotionPayloadValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestEmotionMessageController {

    private MockMvc mockMvc;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    EmotionPayloadValidator emotionPayloadValidator;

    @Mock
    MessageRepository messageRepository;

    @Mock
    EmotionMessageFactory emotionMessageFactory;

    @InjectMocks
    EmotionMessageController emotionMessageController;

    @BeforeEach
    public void setUp(){
        mockMvc = standaloneSetup(emotionMessageController).build();
    }

    @Test
    public void testStatusIsCreated() throws Exception {
        String validPayload = "{\"payload\":\"smile\"}";
        performPostRequest(validPayload, status().isCreated());
    }

    @Test
    public void testStatusIsPreconditionFailedTooShortPayload() throws Exception {
        String tooShortPayload = "{\"payload\":\"s\"}";
        performPostRequest(tooShortPayload, status().isPreconditionFailed());

        String emptyPayload = "{\"payload\":\"\"}";
        performPostRequest(emptyPayload, status().isPreconditionFailed());

    }

    @Test
    public void testStatusIsPreconditionFailedTooLongPayload() throws Exception {
        String tooLongPayload = "{\"payload\":\"toolooooongpaayload\"}";
        performPostRequest(tooLongPayload, status().isPreconditionFailed());
    }

    @Test
    public void testStatusIsPreconditionFailedContainsDigitsPayload() throws Exception {
        String containsDigitPayload = "{\"payload\":\"hi4\"}";
        performPostRequest(containsDigitPayload, status().isPreconditionFailed());
    }

    private void performPostRequest(String payload, ResultMatcher expectedStatus) throws Exception{
        mockMvc.perform(post("/messages/send_emotion")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(payload))
                .andExpect(expectedStatus)
                .andExpect(content().string(""));
    }
}
