package com.dynonuggets.refonteimplicaction.adapter;

import org.springframework.stereotype.Component;

import com.dynonuggets.refonteimplicaction.dto.ChatGroupDto;
import com.dynonuggets.refonteimplicaction.model.ChatGroup;

@Component
public class ChatGroupAdapter {
    public ChatGroup toModel(ChatGroupDto dto) {
        return ChatGroup.builder()
            .users(dto.getUsers())
            .title(dto.getTitle())
            .description(dto.getDescription())
            // .lastMessage(dto.getLastMessage())
            // .lastMessageSendedAt(dto.getLastMessageSendedAt())
            .build();
    }

    public ChatGroupDto toDto(ChatGroup model) {
        return ChatGroupDto.builder()
        .users(model.getUsers())
        .title(model.getTitle())
        .description(model.getDescription())
        // .lastMessage(model.getLastMessage())
        // .lastMessageSendedAt(model.getLastMessageSendedAt())
        .build();
    }
}
