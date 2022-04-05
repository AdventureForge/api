package com.adventureforge.gameservice.repositories;

import com.adventureforge.gameservice.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Optional<Book> findByUuid(UUID uuid);
}
