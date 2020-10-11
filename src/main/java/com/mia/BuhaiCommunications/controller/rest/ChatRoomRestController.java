package com.mia.BuhaiCommunications.controller.rest;

import com.mia.BuhaiCommunications.model.ChatRoom;
import com.mia.BuhaiCommunications.model.ChatRoomList;
import com.mia.BuhaiCommunications.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class ChatRoomRestController {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @GetMapping("/chatrooms")
    public ChatRoomList showAllChatRooms() {
        return new ChatRoomList(chatRoomRepository.findAll());
    }

    @PostMapping("/chatrooms")
    public ChatRoom createChatRoom(@RequestBody ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }

    @DeleteMapping("/chatrooms")
    public void deleteChatRoom(@RequestBody ChatRoom chatRoom) {
        chatRoomRepository.deleteById(chatRoom.getChatRoomId());
    }

}
