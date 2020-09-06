package com.mia.BuhaiCommunications.controller.rest;

import com.mia.BuhaiCommunications.model.Message;
import com.mia.BuhaiCommunications.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageRestController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageRepository.findAll());
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<Message> getMessage(@PathVariable Integer id) {
        return ResponseEntity.of(messageRepository.findById(id));
    }

    @PostMapping("/message/create")

    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        messageRepository.save(message);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/message/{id}")
    public ResponseEntity<Message> updateMessage(
            @PathVariable Integer id,
            @RequestBody Message message) {

        Message messageUpdated = messageRepository.save(message);

        return new ResponseEntity<Message>(message, HttpStatus.OK);

    }

    @DeleteMapping("/message/delete/{id}")

    public ResponseEntity deleteMessage(@PathVariable Integer id) {
        try {
            messageRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }


    }


}
