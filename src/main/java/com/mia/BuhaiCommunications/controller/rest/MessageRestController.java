package com.mia.BuhaiCommunications.controller.rest;

import com.mia.BuhaiCommunications.model.Message;
import com.mia.BuhaiCommunications.model.MessageList;
import com.mia.BuhaiCommunications.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/messages/{chatRoomId}")
    public MessageList showAllMessagesFromChatRoom(@PathVariable Integer chatRoomId, @RequestParam(required = false) Integer limit) {
        if (limit == null || limit <= 0) {
            limit = 10;
        }
        return new MessageList(
                messageRepository.showAllMessagesFromChatRoom(
                        chatRoomId,
                        PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "messageId"))
                )
        );
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
