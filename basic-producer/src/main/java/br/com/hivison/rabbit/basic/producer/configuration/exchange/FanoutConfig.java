package br.com.hivison.rabbit.basic.producer.configuration.exchange;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "application.rabbit.exchange", havingValue = "fanout")
public class FanoutConfig {

    private final Queue firstQueue;
    private final Queue secondQueue;
    private final Queue thirdQueue;

    public FanoutConfig(Queue firstQueue, Queue secondQueue, Queue thirdQueue) {
        this.firstQueue = firstQueue;
        this.secondQueue = secondQueue;
        this.thirdQueue = thirdQueue;
    }

    @Bean
    public Exchange fanoutExchange() {
        return ExchangeBuilder.fanoutExchange("FANOUT-EXCHANGE").durable(true).build();
    }

    @Bean
    public Binding firstFanoutBinding(Exchange fanoutExchange){
        return bindQueue(firstQueue, fanoutExchange);
    }

    @Bean
    public Binding secondFanoutBinding(Exchange fanoutExchange){
        return bindQueue(secondQueue, fanoutExchange);
    }

    @Bean
    public Binding thirdFanoutBinding(Exchange fanoutExchange){
        return bindQueue(thirdQueue, fanoutExchange);
    }

    private Binding bindQueue(Queue firstFanoutQueue, Exchange fanoutExchange) {
        return BindingBuilder.bind(firstFanoutQueue).to(fanoutExchange).with(StringUtils.EMPTY).noargs();
    }

}
