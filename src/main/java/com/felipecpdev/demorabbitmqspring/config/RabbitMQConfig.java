package com.felipecpdev.demorabbitmqspring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {

    private final ConnectionFactory connectionFactory;

    @Value("${rabbitmq.queues.notification}")
    private String queue;
    @Value("${rabbitmq.queues.notification-one}")
    private String queueB;

    @Value("${rabbitmq.exchanges.internal}")
    private String exchangeInternal;

    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String routingKeyInternal;
    @Value("${rabbitmq.routing-keys.internal-notification-one}")
    private String routingKeyInternalOne;


    @Bean
    public AmqpTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter());
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jacksonConverter());
        return factory;
    }

    @Bean
    public MessageConverter jacksonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    Queue queueA() {
        return new Queue(queue, true);
    }

    @Bean
    Queue queueB() {
        return new Queue(queueB, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(exchangeInternal);
    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queueA()).to(exchange()).with(routingKeyInternal);
    }

    @Bean
    Binding bindingB() {
        return BindingBuilder.bind(queueB()).to(exchange()).with(routingKeyInternalOne);
    }
}
