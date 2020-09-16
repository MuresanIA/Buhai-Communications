package com.mia.BuhaiCommunications.controller.rest;

import com.mia.BuhaiCommunications.model.ChatRoomList;
import com.mia.BuhaiCommunications.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ChatRoomRestController {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @GetMapping("/chatrooms")
    public ChatRoomList showAllChatRooms() {
        ChatRoomList chatRooms = new ChatRoomList(chatRoomRepository.findAll());
        return chatRooms;
    }

}
