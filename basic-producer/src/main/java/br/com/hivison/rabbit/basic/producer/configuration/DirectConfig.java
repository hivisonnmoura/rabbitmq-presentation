package br.com.hivison.rabbit.basic.producer.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class DirectConfig {

    private final Exchange directExchange;
    private final Queue firstQueue;
    private final Queue secondQueue;
    private final Queue jsonQueue;
    private final Queue firstCloneQueue;

    public DirectConfig(Exchange directExchange, Queue firstQueue, Queue secondQueue, Queue jsonQueue, Queue firstCloneQueue) {
        this.directExchange = directExchange;
        this.firstQueue = firstQueue;
        this.secondQueue = secondQueue;
        this.jsonQueue = jsonQueue;
        this.firstCloneQueue = firstCloneQueue;
    }

    @Bean
    public Binding firstDirectBinding() {
        return bindQueue(firstQueue, directExchange, "TO-FIRST-QUEUE");
    }

    @Bean
    public Binding cloneDirectBiding() {
        return bindQueue(firstCloneQueue, directExchange, "TO-FIRST-QUEUE");
    }

    @Bean
    public Binding secondDirectBinding() {
        return bindQueue(secondQueue, directExchange, "TO-SECOND-QUEUE");
    }

    @Bean
    public Binding jsonDirectBinding() {
        return bindQueue(jsonQueue, directExchange, "TO-JSON-QUEUE");
    }

    private Binding bindQueue(Queue firstQueue, Exchange directExchange, String routingKey) {
        return BindingBuilder.bind(firstQueue).to(directExchange).with(routingKey).noargs();
    }


}
