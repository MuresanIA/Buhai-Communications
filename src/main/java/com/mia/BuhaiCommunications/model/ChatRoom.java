package com.mia.BuhaiCommunications.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table()
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer chatRoomId;
    @Column(nullable = false)
    private String queueName;

    @OneToMany(
            mappedBy = "chatRoom",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Message> messages;
}
