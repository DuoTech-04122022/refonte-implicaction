package com.dynonuggets.refonteimplicaction.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;

import com.dynonuggets.refonteimplicaction.adapter.ChatGroupAdapter;
import com.dynonuggets.refonteimplicaction.adapter.UserAdapter;
import com.dynonuggets.refonteimplicaction.dto.ChatGroupDto;
import com.dynonuggets.refonteimplicaction.dto.UserDto;
import com.dynonuggets.refonteimplicaction.exception.NotFoundException;
import com.dynonuggets.refonteimplicaction.model.ChatGroup;
import com.dynonuggets.refonteimplicaction.model.User;
import com.dynonuggets.refonteimplicaction.repository.ChatGroupRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChatGroupsService {
    private final ChatGroupRepository chatGroupRepository;
    private final ChatGroupAdapter chatGroupAdapter;
    private final UserAdapter userAdapter;


    @Transactional()
    public Page<ChatGroupDto> getAll(Pageable pageable) {
        return chatGroupRepository.findAll(pageable).map(chatGroupAdapter::toDto);
    }

    public Page<ChatGroupDto> find(int userId, Pageable pageable) {
        return chatGroupRepository.findAllByUsersById(userId, pageable).map(chatGroupAdapter::toDto);
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

    public ChatGroup addMember(String id, UserDto dto) {
        User user = userAdapter.toChatModel(dto);

        ChatGroup group = chatGroupRepository.findGroupById(id);

        if(group == null) {
            throw new NotFoundException("Group not found");
        }

        group.addUser(user);

        chatGroupRepository.save(group);

        return group;
    }

    public ChatGroup removeMember(String groupId, UserDto dto) {

        ChatGroup group = chatGroupRepository.findGroupById(groupId);

        if(group == null) {
            throw new NotFoundException("Group not found");
        }

        group.removeUser(dto.getId());
        chatGroupRepository.save(group);

        return group;
    }

}
