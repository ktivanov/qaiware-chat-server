package com.qaiware.chatserver.messages.api;


import com.qaiware.chatserver.messages.factories.EmotionMessageFactory;
import com.qaiware.chatserver.messages.repositories.MessageRepository;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestEmotionMessageController {

  @Mock(answer = Answers.CALLS_REAL_METHODS)
  EmotionPayloadValidator emotionPayloadValidator;
  @Mock
  MessageRepository messageRepository;
  @Mock
  EmotionMessageFactory emotionMessageFactory;
  @InjectMocks
  EmotionMessageController emotionMessageController;
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(emotionMessageController).build();
  }

  @Test
  public void testStatusIsCreated() throws Exception {
    String validPayload = "{\"payload\":\"smile\"}";
    performPostRequest(validPayload, MockMvcResultMatchers.status().isCreated());
  }

  @Test
  public void testStatusIsPreconditionFailedTooShortPayload() throws Exception {
    String tooShortPayload = "{\"payload\":\"s\"}";
    performPostRequest(tooShortPayload, MockMvcResultMatchers.status().isPreconditionFailed());

    String emptyPayload = "{\"payload\":\"\"}";
    performPostRequest(emptyPayload, MockMvcResultMatchers.status().isPreconditionFailed());

  }

  @Test
  public void testStatusIsPreconditionFailedTooLongPayload() throws Exception {
    String tooLongPayload = "{\"payload\":\"toolooooongpaayload\"}";
    performPostRequest(tooLongPayload, MockMvcResultMatchers.status().isPreconditionFailed());
  }

  @Test
  public void testStatusIsPreconditionFailedContains_DigitsPayload() throws Exception {
    String containsDigitPayload = "{\"payload\":\"hi4\"}";
    performPostRequest(containsDigitPayload, MockMvcResultMatchers.status().isPreconditionFailed());
  }

  private void performPostRequest(String payload, ResultMatcher expectedStatus) throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.post("/messages/send_emotion")
      .contentType(MediaType.APPLICATION_JSON).content(payload))
      .andExpect(expectedStatus).andExpect(MockMvcResultMatchers.content().string(""));
  }
}
