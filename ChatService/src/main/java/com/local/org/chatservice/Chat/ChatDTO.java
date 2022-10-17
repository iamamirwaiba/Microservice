package com.local.org.ChatService.Chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDTO {
    private int senderId;
    private int recipientId;
    private String data;
    private String type;
    private String status;
    private String chatType;

}
