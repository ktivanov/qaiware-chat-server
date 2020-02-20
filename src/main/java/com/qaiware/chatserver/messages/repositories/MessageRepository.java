package com.qaiware.chatserver.messages.repositories;

import com.qaiware.chatserver.messages.entities.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
