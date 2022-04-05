package com.adventureforge.adventureservice.repositories;

import com.adventureforge.adventureservice.entities.Adventure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdventureRepository extends JpaRepository<Adventure, Integer> {
}
