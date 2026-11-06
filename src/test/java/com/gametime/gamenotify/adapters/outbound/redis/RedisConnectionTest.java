package com.gametime.gamenotify.adapters.outbound.redis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisConnectionTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    @DisplayName("Deve conectar ao Redis e realizar operações de gravacao e leitura com sucesso")
    void shouldConnectAndInteractWithRedis() {
        String key = "test:connection";
        String value = "UP";

        // Grava no Redis
        redisTemplate.opsForValue().set(key, value);

        // Lê do Redis
        String retrievedValue = redisTemplate.opsForValue().get(key);

        // Valida
        assertNotNull(retrievedValue);
        assertEquals(value, retrievedValue);

        // Limpeza
        redisTemplate.delete(key);
    }
}