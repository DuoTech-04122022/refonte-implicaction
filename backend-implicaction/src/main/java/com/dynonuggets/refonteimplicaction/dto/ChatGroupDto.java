package com.dynonuggets.refonteimplicaction.dto;

import lombok.*;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatGroupDto {
    private String id;
    private String title;
    private String description;
    private String[] users;
}