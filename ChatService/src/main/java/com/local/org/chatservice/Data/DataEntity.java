package com.local.org.ChatService.Data;

import com.local.org.ChatService.Chat.ChatEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="data")
@EqualsAndHashCode
@ToString
public class DataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="data")
    private String data;

    @Column(name="type")
    private String type;

    @Column(name="created_at")
    private LocalDate createdAt;

    @Column(name="updated_at")
    private LocalDate updatedAt;

    @Column(name="chat_status")
    private String status;

    @Column(name="chat_type")
    private int chatType;

    public DataEntity(String data, String type, String status) {
        this.data = data;
        this.type = type;
        this.status = status;
    }
}
