package com.mia.BuhaiCommunications.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table()
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer messageId;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String sender;
    @Column(nullable = false)
    private long timeStamp;

    @ManyToOne(fetch = FetchType.EAGER)
    private ChatRoom chatRoom;

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

}
