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
    public Queue secondQueue(){
        return buildDurableQueue("SECOND-QUEUE");
    }

    @Bean
    public Queue thirdQueue(){
        return buildDurableQueue("THIRD-QUEUE");
    }

    @Bean
    public Queue topicQueue(){
        return buildDurableQueue("allQueue");
    }

    private Queue buildDurableQueue(String queueName){
        return QueueBuilder.durable(queueName).build();
    }
}
