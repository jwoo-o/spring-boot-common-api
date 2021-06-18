package com.gen.api.global.config.rabbitmq;

/**import com.gen.bluexray.global.config.websocket.RabbitSubscriber;
import com.gen.bluexray.global.config.websocket.RedisSubscriber;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.messaging.converter.MessageConverter;*/

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-12-03
 * Time: 오전 10:49
 */
public class RabbitConfiguration {

  /**  @Bean
    public TopicExchange topic() {
        return new TopicExchange("amq.topic");
    }


    @Bean
    public Queue queue() { return new Queue("realTime",false);}


    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRoutingKey(queue().getName());
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public SimpleMessageListenerContainer container(final ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queue().getName());

        return container;
    }
*/

}
