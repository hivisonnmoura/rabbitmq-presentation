package br.com.hivison.rabbit.basic.producer.controller;

import br.com.hivison.rabbit.basic.producer.dto.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exchanges")
public class ExchangeController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ExchangeController.class);

    private final RabbitTemplate rabbitTemplate;
    private final MessageConverter messageConverter;

    public ExchangeController(RabbitTemplate rabbitTemplate, MessageConverter messageConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageConverter = messageConverter;
    }

    @PostMapping("{exchange}/{routingKey}")
    public HttpEntity<?> postOnExchange(
            @PathVariable(required = false) String exchange,
            @PathVariable(required = false) String routingKey,
            @RequestBody Contact contact) {
        LOGGER.info("Sending message:{} to exchange: {} and queue:{}", contact, exchange, routingKey);
        rabbitTemplate.convertAndSend(exchange, routingKey, contact);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{headerValue}")
    public HttpEntity<?> postOnHeadersExchange(
            @PathVariable String headerValue,
            @RequestBody Contact contact) {
        LOGGER.info("Sending message:{} to exchange: HEADERS-EXCHANGE with header:{}", contact, headerValue);
        final var messageProperties = new MessageProperties();
        messageProperties.setHeader("destination", headerValue);
        final var message = messageConverter.toMessage(contact, messageProperties);
        rabbitTemplate.send("HEADERS-EXCHANGE", "", message);
        return ResponseEntity.ok().build();

    }

    @PostMapping("{routingKey}")
    public HttpEntity<?> postOnDefaultExchange(
            @PathVariable(required = false) String routingKey,
            @RequestBody Contact contact) {
        LOGGER.info("Sending message:{} to exchange: Default and queue:{}", contact, routingKey);
        rabbitTemplate.convertAndSend("", routingKey, contact);
        return ResponseEntity.ok().build();
    }
}
