package com.dynonuggets.refonteimplicaction.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
