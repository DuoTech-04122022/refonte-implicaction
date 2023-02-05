package com.dynonuggets.refonteimplicaction.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Document("message")
@AllArgsConstructor
public class ChatMessage {
    @Id
    private String id;
    private String type;
    private String content;
    private String sender;
    
    @Indexed(name = "group_id")
    private String groupId;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime sendedAt;
}