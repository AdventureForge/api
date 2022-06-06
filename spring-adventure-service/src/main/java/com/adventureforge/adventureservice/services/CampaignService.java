package com.adventureforge.adventureservice.services;

import com.adventureforge.adventureservice.entities.Adventure;
import com.adventureforge.adventureservice.entities.Campaign;
import com.adventureforge.adventureservice.exceptions.EntityNotFoundException;
import com.adventureforge.adventureservice.repositories.CampaignRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private static final String UUID_PARAM = "uuid";

    public Page<Campaign> findAllPaginatedFilteredByUserCreated(String username, Pageable pageable) {
        return this.campaignRepository.findAllByUserCreated(username, pageable);
    }

    public Campaign findByUuidAndUsername(UUID uuid, String username) {
        return this.campaignRepository.findByUuidAndUserCreated(uuid, username)
                .orElseThrow(() -> new EntityNotFoundException(Campaign.class, UUID_PARAM, uuid));
    }

    public Campaign create(Campaign campaign) {
        campaign.setUuid(UUID.randomUUID());
        return this.campaignRepository.save(campaign);
    }

    public Campaign createWithDependencies(Campaign campaign) {
        Adventure adventure;
        return this.create(campaign);
    }

    //TODO set adventures properly after db call
    public Campaign update(UUID uuid, Campaign campaignToUpdate, String username) {
        return this.campaignRepository.findByUuidAndUserCreated(uuid, username)
                .map(campaignFromDb -> this.campaignRepository.save(
                        Campaign.builder()
                                .id(campaignFromDb.getId())
                                .uuid(campaignFromDb.getUuid())
                                .title(campaignToUpdate.getTitle())
                                .description(campaignToUpdate.getDescription())
                                .adventures(campaignToUpdate.getAdventures())
                                .build()
                )).orElseThrow(() -> new EntityNotFoundException(
                        Campaign.class, UUID_PARAM, uuid, "campaignToUpdate", campaignToUpdate)
                );
    }

    public void deleteByUuid(UUID uuid, String username) {
        Campaign campaignToDelete = this.findByUuidAndUsername(uuid, username);
        this.campaignRepository.deleteById(campaignToDelete.getId());
    }

    public void deleteByListOfUuids(List<UUID> uuids, String username) {
        uuids.forEach(uuid -> this.deleteByUuid(uuid, username));
    }

}
