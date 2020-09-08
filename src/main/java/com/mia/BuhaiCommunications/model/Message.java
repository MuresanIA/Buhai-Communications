package com.mia.BuhaiCommunications.model;

import com.mia.BuhaiCommunications.Sender.Sender;
import com.mia.BuhaiCommunications.receiver.Receiver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int messageId;

    private String text;

    private Timestamp timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    private Receiver receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sender sender;
}
