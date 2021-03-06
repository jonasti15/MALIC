package com.malic.musker.comunication;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    public static final String QUEUE_DATOS = "colaAvistamientos";
    public final static String EXCHANGE_DATOS = "avistamiento";

    @Bean
    public Queue queue(){
        return new Queue(QUEUE_DATOS);
    }

    @Bean
    public FanoutExchange exchange(){
        return new FanoutExchange(EXCHANGE_DATOS);
    }

    @Bean
    public Binding binding(Queue queue, FanoutExchange exchange){
        return BindingBuilder
                .bind(queue)
                .to(exchange);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return  template;
    }
}
