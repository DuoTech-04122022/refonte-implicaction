package com.dynonuggets.refonteimplicaction.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.dynonuggets.refonteimplicaction.model.ChatMessage;

public interface MessageRepository extends MongoRepository<ChatMessage, String> {

}