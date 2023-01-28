package com.dynonuggets.refonteimplicaction.controller;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
@RequestMapping
public class MessageController {
    private final ChatMessageAdapter messageAdapter;
    private final MessageService messageService;

    @PostMapping("/messages")
    public ResponseEntity<ChatMessageDto> add(@RequestBody ChatMessageDto dto) {

        ChatMessage message = messageService.add(dto);
        ChatMessageDto messageDto = messageAdapter.toDto(message);

        return ResponseEntity.ok(messageDto);
    }
    @GetMapping("/messages/{groupId}")
    public ResponseEntity<Page<ChatMessageDto>> find(
            @PathVariable String groupId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "rows", defaultValue = "10") int rows) {
        Pageable pageable = PageRequest.of(page, rows);
        Page<ChatMessageDto> messages = messageService.find(groupId, pageable);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<Optional<ChatMessage>> findMessage(@PathVariable String id) {
        return ResponseEntity.ok(messageService.findById(id));
    }

    @MessageMapping("/send/{groupId}")
    @SendTo("/topic/chat/{groupId}")
    public ResponseEntity<ChatMessageDto> send(@RequestBody ChatMessageDto dto) {

        ChatMessage message = messageService.add(dto);
        ChatMessageDto messageDto = messageAdapter.toDto(message);

        return ResponseEntity.ok(messageDto);
    }
}