package com.local.org.ChatService.Reply;

import com.local.org.ChatService.Data.DataEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="participant")
@EqualsAndHashCode
@ToString
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="which_data_id")
    private int whichDataId;

    @ManyToOne
    @JoinColumn(name = "fk_which_data_id")
    private DataEntity dataList;

}
