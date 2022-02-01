package br.com.hivison.rabbit.basic.producer.configuration.exchange;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnProperty(value = "application.rabbit.exchange", havingValue = "direct")
public class DirectConfig {

    private final Queue firstQueue;
    private final Queue secondQueue;
    private final Queue thirdQueue;

    public DirectConfig(Queue firstQueue, Queue secondQueue, Queue thirdQueue) {
        this.firstQueue = firstQueue;
        this.secondQueue = secondQueue;
        this.thirdQueue = thirdQueue;
    }

    @Bean
    public Exchange directExchange() {
        return ExchangeBuilder.directExchange("DIRECT-EXCHANGE").durable(true).build();
    }

    @Bean
    public Binding firstDirectBinding(Exchange directExchange) {
        return bindQueue(firstQueue, directExchange, "TO.FIRST.QUEUE");
    }

    @Bean
    public Binding secondDirectBinding(Exchange directExchange) {
        return bindQueue(secondQueue, directExchange, "TO.SECOND.QUEUE");
    }

    @Bean
    public Binding thirdDirectBinding(Exchange directExchange) {
        return bindQueue(thirdQueue, directExchange, "TO.THIRD.QUEUE");
    }

    private Binding bindQueue(Queue queue, Exchange exchange, String routingKey) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();
    }


}
