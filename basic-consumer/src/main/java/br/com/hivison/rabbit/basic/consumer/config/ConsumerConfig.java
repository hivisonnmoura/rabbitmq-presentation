package br.com.hivison.rabbit.basic.consumer.config;

import br.com.hivison.rabbit.basic.consumer.service.BasicListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

    private final ConnectionFactory connectionFactory;
    private final BasicListener basicListener;

    public ConsumerConfig(
            ConnectionFactory connectionFactory,
            BasicListener basicListener) {
        this.connectionFactory = connectionFactory;
        this.basicListener = basicListener;
    }

    @Bean
    public MessageListenerContainer listenerContainer() {
        final var container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("SECOND-QUEUE");
        container.setMessageListener(basicListener);

        return container;
    }
}
