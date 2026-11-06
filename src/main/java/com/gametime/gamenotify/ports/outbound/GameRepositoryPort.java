package com.gametime.gamenotify.ports.outbound;

import com.gametime.gamenotify.domain.model.Game;

import java.util.List;
import java.util.Optional;

public interface GameRepositoryPort {
    Game save(Game game);
    Optional<Game> findById(Long id);
    List<Game> findAll();
    void deleteById(Long id);
}