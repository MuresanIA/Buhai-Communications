package com.mia.BuhaiCommunications.repository;

import com.mia.BuhaiCommunications.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {

    public ChatRoom findByChatRoomId(Integer chatRoomId);


    @Query("FROM ChatRoom c WHERE c.queueName = :queueName")
    public ChatRoom findByQueueName(String queueName);
}
