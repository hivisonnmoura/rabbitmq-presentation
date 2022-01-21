package br.com.hivison.rabbit.basic.producer.configuration;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExchangeConfig {

    @Bean
    public Exchange directExchange() {
        return ExchangeBuilder.directExchange("DIRECT-EXCHANGE").durable(true).build();
    }
}
