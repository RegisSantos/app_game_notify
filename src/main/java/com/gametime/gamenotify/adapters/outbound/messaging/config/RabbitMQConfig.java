package com.gametime.gamenotify.adapters.outbound.messaging.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String GAME_NOTIFY_QUEUE = "game.notify.queue";

    @Bean
    public Queue gameNotifyQueue() {
        return new Queue(GAME_NOTIFY_QUEUE, true); // true para fila durável
    }
}