package com.adventureforge.gameservice.repositories;

import com.adventureforge.gameservice.entities.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Integer> {

    Optional<Edition> findByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}
