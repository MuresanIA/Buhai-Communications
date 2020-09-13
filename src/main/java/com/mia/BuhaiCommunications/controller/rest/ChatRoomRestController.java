package com.mia.BuhaiCommunications.controller.rest;

import com.mia.BuhaiCommunications.chat.ChatRoom;
import com.mia.BuhaiCommunications.rabbitmq.MyConnectionFactory;
import com.mia.BuhaiCommunications.repository.ChatRoomRepository;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static com.mia.BuhaiCommunications.rabbitmq.MyConnectionFactory.EXCHANGE_NAME;
import static com.mia.BuhaiCommunications.rabbitmq.MyConnectionFactory.ROUTING_KEY;

@RestController
@RequestMapping("/api")
public class ChatRoomRestController {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @PostMapping("/chatroom/create")
    public ResponseEntity<Integer> createChatRoom(@RequestBody ChatRoom chatRoom) {
//        chatRoomRepository.save(chatRoom);
//        return ResponseEntity.ok(chatRoom);
        try {

            byte[] messageBodyBytes = "Hello, world!".getBytes();
            Channel channel = MyConnectionFactory.getChannel();
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, messageBodyBytes);

            channel.basicConsume(MyConnectionFactory.getOrCreateQueue(), false, "buhai-tag" + System.currentTimeMillis(),
                    new DefaultConsumer(channel) {

                        public void handleDelivery(String consumerTag,
                                                   Envelope envelope,
                                                   AMQP.BasicProperties properties,
                                                   byte[] body)
                                throws IOException {
                            long deliveryTag = envelope.getDeliveryTag();
                            String message = new String(body, StandardCharsets.UTF_8);
                            System.out.println(message + " from " + consumerTag);
                            channel.basicAck(deliveryTag, false);
                        }
                    });
            AMQP.Queue.DeclareOk response = channel.queueDeclarePassive(MyConnectionFactory.getOrCreateQueue());
            return ResponseEntity.ok(response.getMessageCount());
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(-1);

    }
}

