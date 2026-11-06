package com.gametime.gamenotify.adapters.outbound.persistence;

import com.gametime.gamenotify.domain.model.Game;
import com.gametime.gamenotify.ports.outbound.GameRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GameRepositoryAdapter implements GameRepositoryPort {

    private final SpringDataGameRepository springDataGameRepository;

    public GameRepositoryAdapter(SpringDataGameRepository springDataGameRepository) {
        this.springDataGameRepository = springDataGameRepository;
    }

    @Override
    public Game save(Game game) {
        GameEntity entity = toEntity(game);
        GameEntity savedEntity = springDataGameRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Game> findById(Long id) {
        return springDataGameRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Game> findAll() {
        return springDataGameRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        springDataGameRepository.deleteById(id);
    }

    // Métodos de Mapeamento (Mapper) entre Domínio e Entidade JPA
    private GameEntity toEntity(Game game) {
        return new GameEntity(
                game.getId(),
                game.getTitle(),
                game.getDescription(),
                game.getGenre(),
                game.getReleaseDate()
        );
    }

    private Game toDomain(GameEntity entity) {
        return new Game(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getGenre(),
                entity.getReleaseDate()
        );
    }
}