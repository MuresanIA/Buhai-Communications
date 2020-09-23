package com.mia.BuhaiCommunications.repository;

import com.mia.BuhaiCommunications.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {

    public ChatRoom findByChatRoomId(Integer chatRoomId);

    public ChatRoom findByQueueName(String queueName);
}
