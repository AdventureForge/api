package com.adventureforge.gameservice.mappers;

import com.adventureforge.gameservice.dto.EditionDTO;
import com.adventureforge.gameservice.entities.BaseEntity;
import com.adventureforge.gameservice.entities.Edition;
import com.adventureforge.gameservice.entities.RolePlayingGame;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EditionMapper {

    DateTimeMapper DATE_TIME_MAPPER_INSTANCE = Mappers.getMapper(DateTimeMapper.class);

    default EditionDTO toDTO(Edition edition) {
        return EditionDTO.builder()
                .id(edition.getId())
                .uuid(edition.getUuid())
                .editionNumber(edition.getEditionNumber())
                .editionTitle(edition.getEditionTitle())
                .rolePlayingGameUuid(edition.getRolePlayingGame().getUuid())
                .collectionsUuids(edition.getBookCollections() == null ?
                        null :
                        edition.getBookCollections()
                                .stream()
                                .map(BaseEntity::getUuid)
                                .collect(Collectors.toSet()))
                .dateCreated(DATE_TIME_MAPPER_INSTANCE.toFormattedString(edition.getDateCreated()))
                .userCreated(edition.getUserCreated())
                .lastModified(DATE_TIME_MAPPER_INSTANCE.toFormattedString(edition.getLastModified()))
                .userModified(edition.getUserModified())
                .build();
    }

    default Edition toEntity(EditionDTO editionDTO) {
        return Edition.builder()
                .editionNumber(editionDTO.getEditionNumber())
                .editionTitle(editionDTO.getEditionTitle())
                .rolePlayingGame(RolePlayingGame.builder().uuid(editionDTO.getRolePlayingGameUuid()).build())
                .build();
    }
}
