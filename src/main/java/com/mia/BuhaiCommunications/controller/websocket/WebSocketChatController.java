package com.mia.BuhaiCommunications.controller.websocket;

import com.mia.BuhaiCommunications.model.Message;
import com.mia.BuhaiCommunications.repository.ChatRoomRepository;
import com.mia.BuhaiCommunications.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketChatController {
    @Autowired
    public MessageRepository messageRepository;
    @Autowired
    public ChatRoomRepository chatRoomRepository;

    @MessageMapping("/hello/{queueName}")
    @SendTo("/topic/greeting-{queueName}")

    public Message sendMessage(@DestinationVariable String queueName, @Payload Message message) {
        message.setTimeStamp(System.currentTimeMillis());
        message.setChatRoom(chatRoomRepository.findByQueueName(queueName));
        messageRepository.save(message);
        return message;
    }

}
