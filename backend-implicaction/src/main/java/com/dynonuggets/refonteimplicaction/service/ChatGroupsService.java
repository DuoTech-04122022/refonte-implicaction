package com.dynonuggets.refonteimplicaction.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;

import com.dynonuggets.refonteimplicaction.adapter.ChatGroupAdapter;
import com.dynonuggets.refonteimplicaction.dto.ChatGroupDto;
import com.dynonuggets.refonteimplicaction.model.ChatGroup;
import com.dynonuggets.refonteimplicaction.repository.ChatGroupRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChatGroupsService {
    private final ChatGroupRepository chatGroupRepository;
    private final ChatGroupAdapter chatGroupAdapter;


    @Transactional()
    public Page<ChatGroupDto> getAll(Pageable pageable) {
        return chatGroupRepository.findAll(pageable).map(chatGroupAdapter::toDto);
    }

    @Transactional()
    public ChatGroup add(ChatGroupDto dto) {

        ChatGroup group = chatGroupAdapter.toModel(dto);
        chatGroupRepository.save(group);
        return group;
    }

    public Optional<ChatGroup> findById(String id) {
        return chatGroupRepository.findById(id);
    }

}
