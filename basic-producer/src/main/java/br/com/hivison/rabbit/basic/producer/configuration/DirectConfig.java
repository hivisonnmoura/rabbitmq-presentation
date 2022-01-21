package br.com.hivison.rabbit.basic.producer.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    private final Exchange directExchange;
    private final Queue firstQueue;
    private final Queue secondQueue;
    private final Queue jsonQueue;

    public DirectConfig(Exchange directExchange, Queue firstQueue, Queue secondQueue, Queue jsonQueue) {
        this.directExchange = directExchange;
        this.firstQueue = firstQueue;
        this.secondQueue = secondQueue;
        this.jsonQueue = jsonQueue;
    }

/*    @PostConstruct
    public void init(){
        firstDirectBinding();
        secondDirectBinding();
        jsonDirectBinding();
    }*/

    @Bean
    public Binding firstDirectBinding() {
        return bindQueue(firstQueue, directExchange, "TO-FIRST-QUEUE");
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
