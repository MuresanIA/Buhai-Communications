package com.mia.BuhaiCommunications.controller.thymeleaf;

import com.mia.BuhaiCommunications.model.ChatRoom;
import com.mia.BuhaiCommunications.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;


    @RequestMapping(method = RequestMethod.GET, value = "/chatrooms")
    public String showAllChatRooms(Model model) {
        model.addAttribute("chatrooms", chatRoomService.findAll());
        return "chatroom/chatroom";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/createchatrooms")
    public String createChatRoom(Model model) {
        model.addAttribute("chatRoom", new ChatRoom());
        return "chatroom/createchatroom";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createchatrooms")
    public String createChatRoom(@ModelAttribute ChatRoom chatRoom) {

        chatRoomService.save(chatRoom);

        return "redirect:/chatrooms";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/editchatroom/{id}")
    public String editChatRoom(Model model, @PathVariable Integer id) {
        ChatRoom chatRoom = chatRoomService.findById(id);
        model.addAttribute("chatRoom", chatRoom);
        return "chatroom/editchatroom";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editchatroom/{id}")
    public String editChatRoom(@ModelAttribute ChatRoom chatRoom, @PathVariable Integer id) {
        chatRoomService.save(chatRoom);
        return "redirect:/chatrooms";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deletechatroom/{id}")
    public String deleteChatRoom(@PathVariable Integer id) {
        chatRoomService.deleteById(id);

        return "redirect:/chatrooms";
    }


    @GetMapping("/websocket/{id}")
    public String getWebsocket(Model model, @PathVariable("id") Integer id) {
        ChatRoom chatRoom = chatRoomService.findById(id);

        model.addAttribute("chatroom", chatRoom);

        String currentUserName = "Anonymous";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }


        model.addAttribute("username", currentUserName);

        return "/websocket/websocket";
    }


}
