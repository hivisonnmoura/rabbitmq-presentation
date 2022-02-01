package br.com.hivison.rabbit.basic.consumer.service;

import br.com.hivison.rabbit.basic.consumer.dto.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;


@Service
public class SpringRabbitBasicConsumer {

    public static final Logger LOGGER = LoggerFactory.getLogger(SpringRabbitBasicConsumer.class);

    private final MessageConverter messageConverter;

    public SpringRabbitBasicConsumer(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    @RabbitListener(queues = {"FIRST-QUEUE", "THIRD-QUEUE"}, concurrency = "1-10")
    public void listenerFirstQueue(Message message) {
        LOGGER.info("Receive message from {}", message.getMessageProperties().getConsumerQueue());
        final var payload = (Contact) messageConverter.fromMessage(message);
        LOGGER.info("Payload: {}", payload);
    }
}
