package com.adventureforge.gameservice.mappers;

import com.adventureforge.gameservice.dto.RolePlayingGameDTO;
import com.adventureforge.gameservice.entities.Edition;
import com.adventureforge.gameservice.entities.RolePlayingGame;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RolePlayingGameMapper {

    default RolePlayingGameDTO toDTO(RolePlayingGame rolePlayingGame) {
        return RolePlayingGameDTO.builder()
                .uuid(rolePlayingGame.getUuid())
                .title(rolePlayingGame.getTitle())
                .subtitle(rolePlayingGame.getSubtitle())
                .pictureUrl(rolePlayingGame.getPictureUrl())
                .websiteUrl(rolePlayingGame.getWebsiteUrl())
                .editionsUuids(rolePlayingGame.getEditions()
                        .stream()
                        .map(Edition::getUuid)
                        .collect(Collectors.toSet()
                        )
                )
                .build();
    }

    RolePlayingGame toEntity(RolePlayingGameDTO rolePlayingGameDTO);
}
