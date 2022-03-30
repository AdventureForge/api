package com.adventureforge.gameservice.repositories;

import com.adventureforge.gameservice.entities.AuthorBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorBookRepository extends JpaRepository<AuthorBook, Integer> {
}
