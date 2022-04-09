package com.adventureforge.adventureservice.repositories;

import com.adventureforge.adventureservice.entities.Npc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NpcRepository extends JpaRepository<Npc, Integer> {
}
