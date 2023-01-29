package com.dynonuggets.refonteimplicaction.adapter;

import com.dynonuggets.refonteimplicaction.dto.ChatMessageDto;
import com.dynonuggets.refonteimplicaction.model.ChatMessage;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageAdapter {

    public ChatMessage toModel(ChatMessageDto dto) {
        return ChatMessage.builder()
            .id(dto.getId())
            .type(dto.getType())
            .content(dto.getContent())
            .sender(dto.getSender())
            .sendedAt(dto.getSendedAt())
            .build();
    }

    public ChatMessageDto toDto(ChatMessage model) {
        return ChatMessageDto.builder()
                .id(model.getId())
            .type(model.getType())
            .content(model.getContent())
            .sender(model.getSender())
            .sendedAt(model.getSendedAt())
            .build();
    }
}