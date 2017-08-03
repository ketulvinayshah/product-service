package com.product.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kshah on 7/31/17.
 */
@Configuration
public class MessagingConfiguration {
    private final String PRODUCTS_EXCHANGE = "products.manager";

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory container = new SimpleRabbitListenerContainerFactory();
        container.setConnectionFactory(connectionFactory);
        container.setMessageConverter(new Jackson2JsonMessageConverter());
        return container;
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(PRODUCTS_EXCHANGE, true, false);
    }

    @Bean
    public Queue upsert(){
        return new Queue("products.upsert");
    }

    @Bean
    public Binding queuesAndBindings(AmqpAdmin admin){
        Binding binding = new Binding("products.upsert", Binding.DestinationType.QUEUE, PRODUCTS_EXCHANGE, "products.upsert", null);
        return binding;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        template.setExchange(PRODUCTS_EXCHANGE);
        return template;
    }
}