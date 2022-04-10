package com.adventureforge.gameservice.repositories;

import com.adventureforge.gameservice.entities.BookCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookCollectionRepository extends JpaRepository<BookCollection, Integer> {

    Optional<BookCollection> findByUuid(UUID uuid);
}
