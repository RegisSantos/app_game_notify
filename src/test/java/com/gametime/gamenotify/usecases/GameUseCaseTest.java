package com.gametime.gamenotify.usecases;

import com.gametime.gamenotify.domain.model.Game;
import com.gametime.gamenotify.ports.outbound.GameRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameUseCaseTest {

    @Mock
    private GameRepositoryPort gameRepositoryPort;

    @InjectMocks
    private GameUseCase gameUseCase;

    private Game gameSample;

    @BeforeEach
    void setUp() {
        gameSample = new Game(
                1L,
                "Elden Ring",
                "Action RPG game",
                "RPG",
                LocalDate.of(2022, 2, 25)
        );
    }

    @Test
    @DisplayName("Deve salvar um jogo com sucesso")
    void shouldSaveGameSuccessfully() {
        when(gameRepositoryPort.save(any(Game.class))).thenReturn(gameSample);

        Game savedGame = gameUseCase.saveGame(gameSample);

        assertNotNull(savedGame);
        assertEquals("Elden Ring", savedGame.getTitle());
        verify(gameRepositoryPort, times(1)).save(gameSample);
    }

    @Test
    @DisplayName("Deve buscar todos os jogos cadastrados")
    void shouldFindAllGames() {
        when(gameRepositoryPort.findAll()).thenReturn(List.of(gameSample));

        List<Game> games = gameUseCase.findAllGames();

        assertFalse(games.isEmpty());
        assertEquals(1, games.size());
        verify(gameRepositoryPort, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve buscar um jogo por ID quando existir")
    void shouldFindGameByIdWhenExists() {
        when(gameRepositoryPort.findById(1L)).thenReturn(Optional.of(gameSample));

        Optional<Game> result = gameUseCase.findGameById(1L);

        assertTrue(result.isPresent());
        assertEquals("Elden Ring", result.get().getTitle());
        verify(gameRepositoryPort, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve deletar um jogo por ID")
    void shouldDeleteGameById() {
        doNothing().when(gameRepositoryPort).deleteById(1L);

        gameUseCase.deleteGame(1L);

        verify(gameRepositoryPort, times(1)).deleteById(1L);
    }
}