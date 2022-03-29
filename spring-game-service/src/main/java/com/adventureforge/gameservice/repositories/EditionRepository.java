package com.adventureforge.gameservice.repositories;

import com.adventureforge.gameservice.entities.Edition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditionRepository extends JpaRepository<Edition, Integer> {
}
