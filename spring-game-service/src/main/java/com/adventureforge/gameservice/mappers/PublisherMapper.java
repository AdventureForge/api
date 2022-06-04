package com.adventureforge.gameservice.mappers;

import com.adventureforge.gameservice.dto.PublisherDTO;
import com.adventureforge.gameservice.entities.BaseEntity;
import com.adventureforge.gameservice.entities.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    DateTimeMapper DATE_TIME_MAPPER_INSTANCE = Mappers.getMapper(DateTimeMapper.class);

    default PublisherDTO toDTO(Publisher publisher) {
        return PublisherDTO.builder()
                .uuid(publisher.getUuid())
                .name(publisher.getName())
                .description(publisher.getDescription())
                .websiteUrl(publisher.getWebsiteUrl())
                .logo(publisher.getLogo())
                .collectionsUuids(
                        publisher.getBookCollections() == null ?
                                null :
                                publisher.getBookCollections()
                                        .stream()
                                        .map(BaseEntity::getUuid)
                                        .collect(Collectors.toSet()))
                .dateCreated(DATE_TIME_MAPPER_INSTANCE.toFormattedString(publisher.getDateCreated()))
                .userCreated(publisher.getUserCreated())
                .lastModified(DATE_TIME_MAPPER_INSTANCE.toFormattedString(publisher.getLastModified()))
                .userModified(publisher.getUserModified())
                .build();
    }

    Publisher toEntity(PublisherDTO publisherDTO);
}
