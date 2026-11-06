package com.gametime.gamenotify.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataGameRepository extends JpaRepository<GameEntity, Long> {
}