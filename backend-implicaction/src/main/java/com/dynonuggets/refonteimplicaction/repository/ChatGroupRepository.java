package com.dynonuggets.refonteimplicaction.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.domain.Pageable;

import com.dynonuggets.refonteimplicaction.model.ChatGroup;

public interface ChatGroupRepository extends MongoRepository<ChatGroup, String> {
    ChatGroup findGroupById(String id);

    @Query(value = "{ 'users.$_id' : ?0 }")
    Page<ChatGroup> findAllByUsersById(int userId, Pageable pageable);

}