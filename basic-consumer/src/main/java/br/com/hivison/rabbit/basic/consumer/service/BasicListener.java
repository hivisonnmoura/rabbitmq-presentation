package br.com.hivison.rabbit.basic.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class BasicListener implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicListener.class);

    @Override
    public void onMessage(Message message) {
        LOGGER.info("Receive message from {}.", message.getMessageProperties().getConsumerQueue());
        final var payload = new String(message.getBody());
        LOGGER.info("Payload: {}", payload);
    }
}
