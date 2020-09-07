package com.mia.BuhaiCommunications.service;

import com.mia.BuhaiCommunications.model.Message;
import com.mia.BuhaiCommunications.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public void save(Message message) {
        messageRepository.save(message);
    }

    public Message findById(Integer id) {
        Optional<Message> message = messageRepository.findById(id);
        if (message.isPresent()) {
            return message.get();
        }
        return null;
    }

    public void deleteById(Integer id) {
        messageRepository.deleteById(id);
    }
}
