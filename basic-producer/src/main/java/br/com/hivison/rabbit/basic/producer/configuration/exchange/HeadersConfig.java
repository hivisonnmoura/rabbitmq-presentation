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

import java.util.Map;

@Configuration
@ConditionalOnProperty(value = "application.rabbit.exchange", havingValue = "headers")
public class HeadersConfig {

    private final Queue firstQueue;
    private final Queue secondQueue;
    private final Queue thirdQueue;

    public HeadersConfig(Queue firstQueue, Queue secondQueue, Queue thirdQueue) {
        this.firstQueue = firstQueue;
        this.secondQueue = secondQueue;
        this.thirdQueue = thirdQueue;
    }

    @Bean
    public Exchange headersExchange() {
        return ExchangeBuilder.headersExchange("HEADERS-EXCHANGE").durable(true).build();
    }

    @Bean
    public Binding firstHeadersFanoutBinding(Exchange headersExchange) {
        return bindQueue(firstQueue, headersExchange, "QUEUE.ONE");
    }

    @Bean
    public Binding secondHeadersBinding(Exchange headersExchange) {
        return bindQueue(secondQueue, headersExchange, "QUEUE.TWO");
    }

    @Bean
    public Binding thirdHeadersBinding(Exchange headersExchange) {
        return bindQueue(thirdQueue, headersExchange, "QUEUE.THREE");
    }

    private Binding bindQueue(Queue queue, Exchange exchange, String match) {
        final Map<String, Object> headerMatch = Map.of(
                "x-match", "any",
                "destination", match);

        return BindingBuilder.bind(queue).to(exchange).with(StringUtils.EMPTY).and(headerMatch);
    }


}
