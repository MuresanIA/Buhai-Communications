package com.mia.BuhaiCommunications.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
                    value = "com.mia.BuhaiCommunications.chat.PostgreSQLUUIDGenerationStrategy"
            )
    )

    private UUID id;

    private String name;

}
