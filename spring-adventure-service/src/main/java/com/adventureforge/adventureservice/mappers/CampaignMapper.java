package com.adventureforge.adventureservice.mappers;

import com.adventureforge.adventureservice.dto.CampaignDTO;
import com.adventureforge.adventureservice.entities.Adventure;
import com.adventureforge.adventureservice.entities.BaseEntity;
import com.adventureforge.adventureservice.entities.Campaign;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = LocaleMapper.class)
public interface CampaignMapper {

    DateTimeMapper DATE_TIME_MAPPER_INSTANCE = Mappers.getMapper(DateTimeMapper.class);

    default CampaignDTO toDTO(Campaign campaign) {
        return CampaignDTO.builder()
                .uuid(campaign.getUuid())
                .title(campaign.getTitle())
                .description(campaign.getDescription())
                .adventuresUuid(campaign.getAdventures() == null ?
                        null :
                        campaign.getAdventures()
                                .stream()
                                .map(BaseEntity::getUuid)
                                .collect(Collectors.toSet()))
                .build();
    }

    default Campaign toEntity(CampaignDTO campaignDTO) {
        return Campaign.builder()
                .uuid(campaignDTO.getUuid())
                .title(campaignDTO.getTitle())
                .description(campaignDTO.getDescription())
                .adventures(campaignDTO
                        .getAdventuresUuid()
                        .stream()
                        .map(uuid -> Adventure.builder().uuid(uuid).build())
                        .collect(Collectors.toSet())
                )
                .build();
    }
}
