package com.mia.BuhaiCommunications.chat;

import com.mia.BuhaiCommunications.Sender.Sender;
import com.mia.BuhaiCommunications.receiver.Receiver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "pg-uuid"
    )
    @GenericGenerator(
            name = "pg-uuid",
            strategy = "uuid2",
            parameters = @Parameter(
                    name = "uuid_gen_strategy_class",
                    value = "com.vladmihalcea.book.hpjp.hibernate.identifier.uuid.PostgreSQLUUIDGenerationStrategy"
            )
    )

    private UUID id;

    private String chatRoomName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Sender sender;

    @ManyToOne(fetch = FetchType.LAZY)
    private Receiver receiver;

}
