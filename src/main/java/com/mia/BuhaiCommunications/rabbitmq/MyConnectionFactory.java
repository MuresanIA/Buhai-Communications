package com.mia.BuhaiCommunications.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MyConnectionFactory {

    private static Connection conn;
    private static Channel channel;
    private static String queueName;

    public static final String ROUTING_KEY = "routing-key";
    public static  final String EXCHANGE_NAME = "buhai-exchange";

    public static Connection getConnection() throws IOException, TimeoutException {
        if (conn == null) {
            ConnectionFactory factory = new ConnectionFactory();
            // "guest"/"guest" by default, limited to localhost connections
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");
            factory.setHost("localhost");
            factory.setPort(5672);
            conn = factory.newConnection();
        }
        return conn;
    }

    public static Channel getChannel() throws IOException, TimeoutException {
        if (channel == null) {
            channel = getConnection().createChannel();
        }
        return channel;
    }

    public static String getOrCreateQueue() {
        if (queueName == null) {
            try {
                Channel channel = MyConnectionFactory.getChannel();
                channel.exchangeDeclare(EXCHANGE_NAME, "direct", true);
                queueName = channel.queueDeclare().getQueue();

                channel.queueBind(queueName, EXCHANGE_NAME, ROUTING_KEY);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return queueName;
    }
}
