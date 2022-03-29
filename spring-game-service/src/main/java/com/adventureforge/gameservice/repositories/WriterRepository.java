package com.adventureforge.gameservice.repositories;

import com.adventureforge.gameservice.entities.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Integer> {
}
