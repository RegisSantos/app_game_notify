package com.gametime.gamenotify.adapters.outbound.messaging;

import com.gametime.gamenotify.adapters.outbound.messaging.config.RabbitMQConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RabbitMQIntegrationTest {

    @Autowired
    private GameMessageProducer gameMessageProducer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    @DisplayName("Deve enviar uma mensagem para a fila do RabbitMQ com sucesso")
    void shouldSendAndReceiveMessageFromRabbitMQ() {
        String payload = "Novo jogo lançado: Elden Ring!";

        // Envia a mensagem via Producer
        gameMessageProducer.sendGameNotification(payload);

        // Consome a mensagem diretamente da fila para validação
        Object receivedMessage = rabbitTemplate.receiveAndConvert(RabbitMQConfig.GAME_NOTIFY_QUEUE);

        assertNotNull(receivedMessage);
        assertEquals(payload, receivedMessage.toString());
    }
}