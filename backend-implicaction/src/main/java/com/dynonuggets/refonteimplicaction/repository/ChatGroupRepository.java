package com.dynonuggets.refonteimplicaction.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.dynonuggets.refonteimplicaction.model.ChatGroup;

public interface ChatGroupRepository extends MongoRepository<ChatGroup, String> {

}