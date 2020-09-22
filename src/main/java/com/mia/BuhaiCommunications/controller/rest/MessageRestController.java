package com.mia.BuhaiCommunications.controller.rest;

import com.mia.BuhaiCommunications.model.Message;
import com.mia.BuhaiCommunications.model.MessageList;
import com.mia.BuhaiCommunications.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageRestController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/messages")
    public MessageList showAllMessages() {
        return new MessageList(messageRepository.findAll());
    }

    @PostMapping("/messages")
    public Message createMessage(@RequestBody Message message) {
        return messageRepository.save(message);
    }

    @DeleteMapping("/messages")
    public void deleteMessage(@RequestBody Message message) {
        messageRepository.deleteById(message.getMessageId());
    }
}
