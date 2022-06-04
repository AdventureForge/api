package com.adventureforge.gameservice.mappers;

import com.adventureforge.gameservice.dto.RolePlayingGameDTO;
import com.adventureforge.gameservice.entities.Edition;
import com.adventureforge.gameservice.entities.RolePlayingGame;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RolePlayingGameMapper {

    DateTimeMapper DATE_TIME_MAPPER_INSTANCE = Mappers.getMapper(DateTimeMapper.class);

    default RolePlayingGameDTO toDTO(RolePlayingGame rolePlayingGame) {
        return RolePlayingGameDTO.builder()
                .uuid(rolePlayingGame.getUuid())
                .title(rolePlayingGame.getTitle())
                .subtitle(rolePlayingGame.getSubtitle())
                .pictureUrl(rolePlayingGame.getPictureUrl())
                .websiteUrl(rolePlayingGame.getWebsiteUrl())
                .editionsUuids(rolePlayingGame.getEditions() == null ?
                        null :
                        rolePlayingGame.getEditions()
                                .stream()
                                .map(Edition::getUuid)
                                .collect(Collectors.toSet()
                                )
                )
                .dateCreated(DATE_TIME_MAPPER_INSTANCE.toFormattedString(rolePlayingGame.getDateCreated()))
                .userCreated(rolePlayingGame.getUserCreated())
                .lastModified(DATE_TIME_MAPPER_INSTANCE.toFormattedString(rolePlayingGame.getLastModified()))
                .userModified(rolePlayingGame.getUserModified())
                .build();
    }

    RolePlayingGame toEntity(RolePlayingGameDTO rolePlayingGameDTO);
}
