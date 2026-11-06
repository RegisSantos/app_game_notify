package com.gametime.gamenotify.adapters.inbound.rest;

import com.gametime.gamenotify.domain.model.Game;
import com.gametime.gamenotify.usecases.GameUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameUseCase gameUseCase;

    public GameController(GameUseCase gameUseCase) {
        this.gameUseCase = gameUseCase;
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game savedGame = gameUseCase.saveGame(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGame);
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameUseCase.findAllGames();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {
        return gameUseCase.findGameById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameUseCase.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}