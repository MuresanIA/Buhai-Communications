package com.mia.BuhaiCommunications.repository;

import com.mia.BuhaiCommunications.model.Message;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    public Message findByMessageId(Integer messageId);

    @Query("FROM Message m WHERE m.chatRoom.chatRoomId = :chatRoomId")
    public List<Message> showAllMessagesFromChatRoom(Integer chatRoomId, Pageable pageable);

    @Override
    <S extends Message> Page<S> findAll(Example<S> example, Pageable pageable);
}
