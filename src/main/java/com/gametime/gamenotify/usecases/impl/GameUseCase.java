
package com.gametime.gamenotify.usecases;

import com.gametime.gamenotify.domain.model.Game;
import com.gametime.gamenotify.ports.outbound.GameRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameUseCase {

    private final GameRepositoryPort gameRepositoryPort;

    public GameUseCase(GameRepositoryPort gameRepositoryPort) {
        this.gameRepositoryPort = gameRepositoryPort;
    }

    public Game saveGame(Game game) {
        // Aqui você pode adicionar regras de negócio futuramente (validações, etc.)
        return gameRepositoryPort.save(game);
    }

    public List<Game> findAllGames() {
        return gameRepositoryPort.findAll();
    }

    public Optional<Game> findGameById(Long id) {
        return gameRepositoryPort.findById(id);
    }

    public void deleteGame(Long id) {
        gameRepositoryPort.deleteById(id);
    }
}