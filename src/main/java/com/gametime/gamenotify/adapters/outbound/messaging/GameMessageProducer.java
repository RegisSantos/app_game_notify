package com.gametime.gamenotify.adapters.outbound.messaging;

import com.gametime.gamenotify.adapters.outbound.messaging.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class GameMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public GameMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendGameNotification(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.GAME_NOTIFY_QUEUE, message);
    }
}