package com.mia.BuhaiCommunications.repository;

import com.mia.BuhaiCommunications.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    public Message findByMessageId(Integer messageId);
}
