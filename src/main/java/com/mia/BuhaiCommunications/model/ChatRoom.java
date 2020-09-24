package com.mia.BuhaiCommunications.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(
            mappedBy = "chatRoom",
            cascade = CascadeType.ALL
    )
    private List<Message> messages;
}
