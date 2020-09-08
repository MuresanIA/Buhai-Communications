package com.mia.BuhaiCommunications.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mia.BuhaiCommunications.Sender.Sender;
import com.mia.BuhaiCommunications.receiver.Receiver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    //TODO: make it unique
    private String password;

    private String nickName;

    private String emailAddress;

    @OneToOne(mappedBy = "user")
    private Receiver receiver;

    @OneToOne(mappedBy = "user")
    private Sender sender;

}
