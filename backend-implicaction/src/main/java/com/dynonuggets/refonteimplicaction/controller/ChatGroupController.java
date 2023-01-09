package com.dynonuggets.refonteimplicaction.controller;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dynonuggets.refonteimplicaction.adapter.ChatGroupAdapter;
import com.dynonuggets.refonteimplicaction.dto.ChatGroupDto;
import com.dynonuggets.refonteimplicaction.dto.UserDto;
import com.dynonuggets.refonteimplicaction.model.ChatGroup;
import com.dynonuggets.refonteimplicaction.service.ChatGroupsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/chat/groups")
public class ChatGroupController {
    private final ChatGroupAdapter groupAdapter;
    private final ChatGroupsService groupService;

    @GetMapping("/user/{user}")
    public ResponseEntity<Page<ChatGroupDto>> find(
            @PathVariable int user,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "rows", defaultValue = "10") int rows) {
        Pageable pageable = PageRequest.of(page, rows);
        Page<ChatGroupDto> groups = groupService.find(user, pageable);
        return ResponseEntity.ok(groups);
    }

    @PostMapping
    public ResponseEntity<ChatGroupDto> add(@RequestBody ChatGroupDto dto) {

        ChatGroup group = groupService.add(dto);
        ChatGroupDto groupDto = groupAdapter.toDto(group);

        return ResponseEntity.ok(groupDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ChatGroup>> findGroup(@PathVariable String id) {
        return ResponseEntity.ok(groupService.findById(id));
    }

    @PatchMapping("/{id}/members/add")
    public ResponseEntity<ChatGroup> putMethodName(@PathVariable String id, @RequestBody UserDto user) {
        return ResponseEntity.ok(groupService.addMember(id, user));
    }
}