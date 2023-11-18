package com.OptimumPool.Authentication.Configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    private String exchange_name="token_exchange";
    private String queue_name="token_queue";

    @Bean
    public DirectExchange directExchange()
    {
        return new DirectExchange(exchange_name) ;
    }
    @Bean
    public Queue registerQueue()
    {
        return new Queue(queue_name);
    }
    @Bean
    public Binding bindExcWithQue()
    {
        return  BindingBuilder.bind(registerQueue()).to(directExchange()).with("token_route");
    }
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory)
    {
        RabbitTemplate rt=new RabbitTemplate((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory);
        rt.setMessageConverter(producerJackson2MessageConrt());
        return rt;

    }
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConrt()
    {
        return new Jackson2JsonMessageConverter();
    }

}
