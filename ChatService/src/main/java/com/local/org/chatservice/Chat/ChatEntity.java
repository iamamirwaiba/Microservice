package com.local.org.ChatService.Chat;


import com.local.org.ChatService.Data.DataEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(name="chat")
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="sender_id")
    private int senderId;
    @Column(name="receiver_id")
    private int receiverId;

    public ChatEntity(int senderId, int receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }
}
