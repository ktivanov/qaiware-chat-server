package com.qaiware.chatserver.messages.api;

import com.qaiware.chatserver.messages.factories.MessageFactory;
import com.qaiware.chatserver.messages.repositories.MessageRepository;
import com.qaiware.chatserver.messages.validators.PayloadValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
abstract class MessageController {
  private final PayloadValidator payloadValidator;
  private final MessageRepository messageRepository;
  private final MessageFactory messageFactory;

  public MessageController(PayloadValidator payloadValidator, MessageRepository messageRepository, MessageFactory messageFactory) {
    this.payloadValidator = payloadValidator;
    this.messageRepository = messageRepository;
    this.messageFactory = messageFactory;
  }

  protected ResponseEntity handleMessage(String payload) {
    if (!payloadValidator.isPayloadValid(payload)) {
      return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("");
    }
    messageRepository.save(messageFactory.createMessage(payload));
    return ResponseEntity.status(HttpStatus.CREATED).body("");
  }
}
