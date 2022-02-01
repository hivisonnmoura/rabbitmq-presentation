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
@ConditionalOnProperty(value = "application.rabbit.exchange", havingValue = "topic")
public class TopicConfig {

    private final Queue firstQueue;
    private final Queue secondQueue;
    private final Queue thirdQueue;
    private final Queue topicQueue;

    public TopicConfig(Queue firstQueue, Queue secondQueue, Queue thirdQueue, Queue topicQueue) {
        this.firstQueue = firstQueue;
        this.secondQueue = secondQueue;
        this.thirdQueue = thirdQueue;
        this.topicQueue = topicQueue;
    }

    @Bean
    public Exchange topicExchange() {
        return ExchangeBuilder.topicExchange("TOPIC-EXCHANGE").durable(true).build();
    }


    @Bean
    public Binding firstTopicFanoutBinding(Exchange topicExchange){
        return bindQueue(firstQueue, topicExchange, "TOPIC.ONE");
    }

    @Bean
    public Binding secondTopicBinding(Exchange topicExchange){
        return bindQueue(secondQueue, topicExchange, "TOPIC.TWO");
    }

    @Bean
    public Binding thirdTopicBinding(Exchange topicExchange){
        return bindQueue(thirdQueue, topicExchange, "TOPIC.THREE");
    }

    @Bean
    public Binding allQueueTopicBinding(Exchange topicExchange){
        return bindQueue(topicQueue, topicExchange, "TOPIC.*");
    }

    private Binding bindQueue(Queue queue, Exchange exchange, String routingKey) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey).noargs();
    }

}
