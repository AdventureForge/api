package com.adventureforge.gameservice.repositories;

import com.adventureforge.gameservice.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    Optional<Publisher> findByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);
}
