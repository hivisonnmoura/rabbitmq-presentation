package br.com.hivison.rabbit.basic.producer.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class QueueConfig {

    @Bean
    public Queue firstQueue(){
        return buildDurableQueue("FIRST-QUEUE");
    }

    @Bean
    public Queue firstCloneQueue(){
        return buildDurableQueue("FIRST-CLONE-QUEUE");
    }

    @Bean
    public Queue secondQueue(){
        return buildDurableQueue("SECOND-QUEUE");
    }

    @Bean
    public Queue jsonQueue(){
        return buildDurableQueue("JSON-QUEUE");
    }

    private Queue buildDurableQueue(String queueName){
        return QueueBuilder.durable(queueName).build();
    }
}
