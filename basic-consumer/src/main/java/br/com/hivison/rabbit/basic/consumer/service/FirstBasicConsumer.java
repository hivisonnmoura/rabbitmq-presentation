package br.com.hivison.rabbit.basic.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FirstBasicConsumer {

    public static final Logger LOGGER = LoggerFactory.getLogger(FirstBasicConsumer.class);

    @RabbitListener(queues = "FIRST-QUEUE")
    public void listenerFirstQueue(Message message){
        LOGGER.info("Receive message from {}", message.getMessageProperties().getConsumerQueue());
        final var payload = new String(message.getBody());
        LOGGER.info("Payload: {}", payload);
    }
}
