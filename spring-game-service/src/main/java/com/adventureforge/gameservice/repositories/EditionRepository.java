package com.adventureforge.gameservice.repositories;

import com.adventureforge.gameservice.entities.Edition;
import com.adventureforge.gameservice.entities.RolePlayingGame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Integer> {

    Optional<Edition> findByUuid(UUID uuid);

    Page<Edition> findAllByRolePlayingGame(RolePlayingGame rolePlayingGame, Pageable pageable);

    boolean existsByUuid(UUID uuid);
}
