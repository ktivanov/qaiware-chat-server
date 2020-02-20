package com.qaiware.chatserver.messages.web;

import com.qaiware.chatserver.messages.entities.EmotionMessage;
import com.qaiware.chatserver.messages.repositories.MessageRepository;
import com.qaiware.chatserver.messages.factories.MessageFactory;
import com.qaiware.chatserver.messages.validators.PayloadValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmotionMessageController extends MessageController {

  @Autowired
  public EmotionMessageController(@Qualifier("emotionPayloadValidator") PayloadValidator payloadValidator,
                                  MessageRepository messageRepository,
                                  @Qualifier("emotionMessageFactory") MessageFactory<EmotionMessage> messageFactory) {
    super(payloadValidator, messageRepository, messageFactory);
  }

  @PostMapping("/send_emotion")
  public ResponseEntity<String> sendEmotion(@RequestBody TextPayloadDto payloadDto) {
    return handleMessage(payloadDto.getPayload());
  }
}