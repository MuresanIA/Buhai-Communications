package com.mia.BuhaiCommunications.controller.rest;

import com.mia.BuhaiCommunications.model.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketChatController {
    @MessageMapping("/hello/{queueName}")
    @SendTo("/topic/greeting-{queueName}")
    public Message sendMessage(@DestinationVariable String queueName, @Payload Message message) {
        return message;
    }

}
