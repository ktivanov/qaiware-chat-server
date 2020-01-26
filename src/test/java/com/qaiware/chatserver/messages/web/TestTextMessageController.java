package com.qaiware.chatserver.messages.web;

import com.qaiware.chatserver.messages.domain.MessageRepository;
import com.qaiware.chatserver.messages.factories.TextMessageFactory;
import com.qaiware.chatserver.messages.validators.TextPayloadValidator;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestTextMessageController {

    private MockMvc mockMvc;

    @Mock
    TextMessageFactory textMessageFactory;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    TextPayloadValidator textPayloadValidator;

    @Mock
    MessageRepository messageRepository;

    @InjectMocks
    private TextMessageController textMessageController;

    @BeforeEach
    public void setUp(){
        mockMvc = standaloneSetup(textMessageController).build();
    }

    @Test
    public void testStatusIsCreated() throws Exception {
        String validPayload = "{\"payload\":\"Hello!\"}";
        performPostRequest(validPayload, status().isCreated());
    }

    @Test
    public void testStatusIsPreconditionFailedTooShortPayload() throws Exception {
        String tooShortPayload = "{\"payload\":\"\"}";
        performPostRequest(tooShortPayload, status().isPreconditionFailed());
    }

    @Test
    public void testStatusIsPreconditionFailedTooLongPayload() throws Exception {
        String tooLongPayload = "{\"payload\":\"testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttestt\"}";
        performPostRequest(tooLongPayload, status().isPreconditionFailed());
    }


    private void performPostRequest(String validPayload, ResultMatcher expectedStatus) throws Exception {
        mockMvc.perform(post("/messages/send_text")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(validPayload))
                .andExpect(expectedStatus)
                .andExpect(content().string(""));
    }
}
