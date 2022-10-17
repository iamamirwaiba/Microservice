package com.local.org.ChatService.ChatRoom;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="chat_room")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="chat_id")
    private int chatId;
    @Column(name="sender_id")
    private int senderId;
    @Column(name="recipient_id")
    private int recipientId;
}
