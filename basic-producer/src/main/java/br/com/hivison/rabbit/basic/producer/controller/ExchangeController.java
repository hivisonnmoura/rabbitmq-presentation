package br.com.hivison.rabbit.basic.producer.controller;

import br.com.hivison.rabbit.basic.producer.dto.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    public ExchangeController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("{exchange}/{routingKey}")
    public HttpEntity<?> postOnExchange(
            @PathVariable String exchange,
            @PathVariable String routingKey,
            @RequestBody Contact contact) {
        LOGGER.info("Sending message:{} to queue: {}", contact, routingKey);
        rabbitTemplate.convertAndSend(exchange, routingKey, contact);
        return ResponseEntity.ok().build();
    }
}
