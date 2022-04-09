package com.adventureforge.adventureservice.repositories;

import com.adventureforge.adventureservice.entities.Appendix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppendixRepository extends JpaRepository<Appendix, Integer> {
}
