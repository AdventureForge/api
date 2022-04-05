package com.adventureforge.adventureservice.repositories;

import com.adventureforge.adventureservice.entities.Scene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SceneRepository extends JpaRepository<Scene, Integer> {
}
