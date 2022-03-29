package com.adventureforge.gameservice.repositories;

import com.adventureforge.gameservice.entities.BookCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCollectionRepository extends JpaRepository<BookCollection, Integer> {
}
