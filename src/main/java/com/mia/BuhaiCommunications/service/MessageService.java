package com.mia.BuhaiCommunications.service;

import com.mia.BuhaiCommunications.model.Message;
import com.mia.BuhaiCommunications.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MessageService")
public class MessageService {
    @Autowired
    public MessageRepository messageRepository;

    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    public Message findById(Integer id) {
        return messageRepository.findByMessageId(id);
    }


}
