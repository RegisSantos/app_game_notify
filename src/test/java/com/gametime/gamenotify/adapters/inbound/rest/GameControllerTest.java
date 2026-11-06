package com.gametime.gamenotify.adapters.inbound.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gametime.gamenotify.domain.model.Game;
import com.gametime.gamenotify.usecases.GameUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameUseCase gameUseCase;

    private ObjectMapper objectMapper;
    private Game gameSample;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        gameSample = new Game(
                1L,
                "The Witcher 3",
                "RPG de mundo aberto",
                "RPG",
                LocalDate.of(2015, 5, 19)
        );
    }

    @Test
    @DisplayName("POST /api/games - Deve criar um novo jogo e retornar 201 Created")
    void shouldCreateGameAndReturn201() throws Exception {
        when(gameUseCase.saveGame(any(Game.class))).thenReturn(gameSample);

        mockMvc.perform(post("/api/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gameSample)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("The Witcher 3"))
                .andExpect(jsonPath("$.genre").value("RPG"));
    }

    @Test
    @DisplayName("GET /api/games - Deve listar todos os jogos e retornar 200 OK")
    void shouldGetAllGamesAndReturn200() throws Exception {
        when(gameUseCase.findAllGames()).thenReturn(List.of(gameSample));

        mockMvc.perform(get("/api/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("The Witcher 3"));
    }

    @Test
    @DisplayName("GET /api/games/{id} - Deve retornar 200 OK quando o jogo for encontrado")
    void shouldGetGameByIdAndReturn200() throws Exception {
        when(gameUseCase.findGameById(1L)).thenReturn(Optional.of(gameSample));

        mockMvc.perform(get("/api/games/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("The Witcher 3"));
    }

    @Test
    @DisplayName("GET /api/games/{id} - Deve retornar 404 Not Found quando o jogo nao existir")
    void shouldReturn404WhenGameNotFound() throws Exception {
        when(gameUseCase.findGameById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/games/99"))
                .andExpect(status().isNotFound());
    }
}