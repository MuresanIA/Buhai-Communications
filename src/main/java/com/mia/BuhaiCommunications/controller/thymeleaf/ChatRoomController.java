package com.mia.BuhaiCommunications.controller.thymeleaf;

import com.mia.BuhaiCommunications.model.ChatRoom;
import com.mia.BuhaiCommunications.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;

    @RequestMapping(method = RequestMethod.GET, value = "/chatrooms")
    public String showAllChatRooms(Model model) {
        model.addAttribute("chatrooms", chatRoomService.findAll());
        return "/ChatRoom/chatroom";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String showHomepage(Model model) {
        return "home";
    }

    @GetMapping("/websocket/{id}")
    public String getWebsocket(Model model, @PathVariable("id") Integer id) {
        ChatRoom chatRoom = chatRoomService.findById(id);

        model.addAttribute("chatroom", chatRoom);

        return "/websocket/websocket";
    }
}
