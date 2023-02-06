package com.dynonuggets.refonteimplicaction.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Pageable;

import com.dynonuggets.refonteimplicaction.model.ChatMessage;

public interface MessageRepository extends MongoRepository<ChatMessage, String> {
    Page<ChatMessage> findByGroupId(String groupId, Pageable pageable);
}