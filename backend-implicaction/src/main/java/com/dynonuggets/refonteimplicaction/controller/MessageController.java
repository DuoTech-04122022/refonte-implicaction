package com.dynonuggets.refonteimplicaction.controller;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dynonuggets.refonteimplicaction.adapter.ChatMessageAdapter;
import com.dynonuggets.refonteimplicaction.dto.ChatMessageDto;
import com.dynonuggets.refonteimplicaction.model.ChatMessage;
import com.dynonuggets.refonteimplicaction.service.MessageService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/chat/messages")
public class MessageController {
    private final ChatMessageAdapter messageAdapter;
    private final MessageService messageService;

    @GetMapping("/{groupId}")
    public ResponseEntity<Page<ChatMessageDto>> find(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "rows", defaultValue = "10") int rows,
            @PathVariable String groupId
            ) {
        Pageable pageable = PageRequest.of(page, rows);
        Page<ChatMessageDto> messages = messageService.find(groupId, pageable);
        return ResponseEntity.ok(messages);
    }

    // @MessageMapping
    // @SendTo("/topic/chat")
    @PostMapping
    public ResponseEntity<ChatMessageDto> add(@RequestBody ChatMessageDto dto) {

        ChatMessage message = messageService.add(dto);
        ChatMessageDto messageDto = messageAdapter.toDto(message);

        // messagingTemplate.convertAndSendToUser(message.getSender(), "/topic/chat", messageDto);

        return ResponseEntity.ok(messageDto);
    }
}