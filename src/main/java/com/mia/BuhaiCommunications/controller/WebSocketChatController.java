package com.mia.BuhaiCommunications.controller;

import com.mia.BuhaiCommunications.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketChatController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message sendMessage(@Payload Message message) {
        return message;
    }
}
