package com.dynonuggets.refonteimplicaction.dto;

import lombok.*;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {
    private String type;
    private String content;
    private String sender;
}