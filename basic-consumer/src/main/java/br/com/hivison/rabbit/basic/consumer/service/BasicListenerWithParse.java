package br.com.hivison.rabbit.basic.consumer.service;

import br.com.hivison.rabbit.basic.consumer.dto.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

@Service
public class BasicListenerWithParse {
    public static final Logger LOGGER = LoggerFactory.getLogger(BasicListenerWithParse.class);

    private final MessageConverter messageConverter;

    public BasicListenerWithParse(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    @RabbitListener(queues = "JSON-QUEUE")
    public void receiveMessageAndParseToObject(Message message){
        LOGGER.info("Receive message from queue: {}", message.getMessageProperties().getConsumerQueue());
        final var contact = (Contact) messageConverter.fromMessage(message);
        LOGGER.info("Contact: {}", contact);
    }
}
