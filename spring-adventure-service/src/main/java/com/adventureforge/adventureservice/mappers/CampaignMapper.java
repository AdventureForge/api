package com.adventureforge.adventureservice.mappers;

import com.adventureforge.adventureservice.dto.CampaignDTO;
import com.adventureforge.adventureservice.entities.Campaign;

public interface CampaignMapper {

    CampaignDTO toDTO(Campaign campaign);

    Campaign toEntity(CampaignDTO campaignDTO);
}
