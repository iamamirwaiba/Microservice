package com.local.org.ChatService.GroupText;


import com.local.org.ChatService.Data.DataEntity;
import com.local.org.ChatService.Participant.ParticipantEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="group_text")
@EqualsAndHashCode
@ToString
public class GroupTextEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;

    @JoinColumn(name="fk_participant_id")
    @OneToMany
    private List<ParticipantEntity> participantId;

    @JoinColumn(name="fk_data_id")
    @OneToMany
    private List<DataEntity> dataId;

    @Column(name="group_image")
    private String groupImage;
    @Column(name="created_at")
    private LocalDate createdAt;
    @Column(name="updated_at")
    private LocalDate updatedAt;

}
