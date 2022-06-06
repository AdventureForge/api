package com.adventureforge.adventureservice.repositories;

import com.adventureforge.adventureservice.entities.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

    public Page<Campaign> findAllByUserCreated(String username, Pageable pageable);

    Optional<Campaign> findByUuidAndUserCreated(UUID uuid, String username);
}
