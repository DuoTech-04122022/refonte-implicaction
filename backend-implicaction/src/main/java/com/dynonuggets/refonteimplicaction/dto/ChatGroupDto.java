package com.dynonuggets.refonteimplicaction.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.dynonuggets.refonteimplicaction.model.ChatMessage;
import com.dynonuggets.refonteimplicaction.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatGroupDto {
    private ArrayList<User> users;
    private String title;
    private String description;
    // private ChatMessage lastMessage;
    // private LocalDateTime lastMessageSendedAt;
}