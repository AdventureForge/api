package com.adventureforge.gameservice.repositories;

import com.adventureforge.gameservice.entities.Illustrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IllustratorRepository extends JpaRepository<Illustrator, Integer> {
}
