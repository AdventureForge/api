package com.adventureforge.gameservice.repositories;

import com.adventureforge.gameservice.entities.RolePlayingGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RolePlayingGameRepository extends JpaRepository<RolePlayingGame, Integer> {

    Optional<RolePlayingGame> findByUuid(UUID uuid);
}
