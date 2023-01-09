package com.dynonuggets.refonteimplicaction.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Iterator;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Document("group")
@AllArgsConstructor
public class ChatGroup {
    @Id
    private String id;
    private List<User> users;
    private String title;
    private String description;

    // private ChatMessage lastMessage;

    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    // private LocalDateTime lastMessageSendedAt;

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(long id) {

        Iterator<User> iterator = this.users.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId().equals(id)) {
                this.users.remove(user);
            }
        }
        
    }
}
