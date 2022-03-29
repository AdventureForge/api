package com.adventureforge.gameservice.repositories;

import com.adventureforge.gameservice.entities.RolePlayingGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePlayingGameRepository extends JpaRepository<RolePlayingGame, Integer> {
}
