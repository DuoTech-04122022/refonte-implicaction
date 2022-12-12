package com.dynonuggets.refonteimplicaction.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import com.dynonuggets.refonteimplicaction.adapter.ChatMessageAdapter;
import com.dynonuggets.refonteimplicaction.dto.ChatMessageDto;
import com.dynonuggets.refonteimplicaction.model.ChatMessage;
import com.dynonuggets.refonteimplicaction.repository.MessageRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatMessageAdapter messageAdapter;

    /**
     * @return la liste paginé de tous les messages
     */
    @Transactional()
    public Page<ChatMessageDto> getAll(Pageable pageable) {
        return messageRepository.findAll(pageable).map(messageAdapter::toDto);
    }

    @Transactional()
    public ChatMessage add(ChatMessageDto dto) {

        ChatMessage message = messageAdapter.toModel(dto);
        messageRepository.save(message);

        return message;
    }

    public Optional<ChatMessage> findById(String id) {
        return messageRepository.findById(id);
    }

}
