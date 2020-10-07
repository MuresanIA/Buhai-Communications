package com.mia.BuhaiCommunications.service;

import com.mia.BuhaiCommunications.model.ChatRoom;
import com.mia.BuhaiCommunications.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ChatRoomService")
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    private ChatRoom chatRoom;

    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAll();
    }

    public ChatRoom findById(Integer id) {
        return chatRoomRepository.findByChatRoomId(id);
    }

    public ChatRoom findByQueueName(String queueName) {
        ChatRoom chatRoom = chatRoomRepository.findByQueueName(queueName);
        return chatRoom;
    }

    public ChatRoom save(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }
}
