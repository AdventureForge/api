package com.adventureforge.gameservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePlayingGameRepository extends JpaRepository<RolePlayingGameRepository, Integer> {
}
