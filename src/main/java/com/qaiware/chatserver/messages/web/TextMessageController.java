package com.qaiware.chatserver.messages.web;

import com.qaiware.chatserver.messages.domain.MessageRepository;
import com.qaiware.chatserver.messages.domain.TextMessage;
import com.qaiware.chatserver.messages.factories.MessageFactory;
import com.qaiware.chatserver.messages.validators.PayloadValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextMessageController extends MessageController{

    @Autowired
    public TextMessageController(@Qualifier("textPayloadValidator") PayloadValidator payloadValidator,
                                 MessageRepository messageRepository,
                                 @Qualifier("textMessageFactory") MessageFactory<TextMessage> messageFactory) {
        super(payloadValidator, messageRepository, messageFactory);
    }

    @PostMapping("/send_text")
    public ResponseEntity<String> sentText(@RequestBody TextPayloadDto payloadDto){
        return handleMessage(payloadDto.getPayload());
    }
}
