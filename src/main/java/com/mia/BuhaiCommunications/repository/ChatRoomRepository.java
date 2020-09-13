package com.mia.BuhaiCommunications.repository;

import com.mia.BuhaiCommunications.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {
}
