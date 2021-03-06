package com.mia.BuhaiCommunications.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String userPassword;
    @Column(nullable = false)
    private String userEmail;

    @OneToMany(
            mappedBy = "users",
            cascade = CascadeType.ALL
    )
    private List<Message> messages;

    @OneToMany(
            mappedBy = "users",
            cascade = CascadeType.ALL
    )
    private List<ChatRoom> chatRooms;

}
