package com.adventureforge.gameservice.mappers;

import com.adventureforge.gameservice.dto.PublisherDTO;
import com.adventureforge.gameservice.entities.BaseEntity;
import com.adventureforge.gameservice.entities.Publisher;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    default PublisherDTO toDTO(Publisher publisher) {
        return PublisherDTO.builder()
                .uuid(publisher.getUuid())
                .name(publisher.getName())
                .description(publisher.getDescription())
                .websiteUrl(publisher.getWebsiteUrl())
                .logo(publisher.getLogo())
                .booksUuids(
                        publisher.getBookCollections() == null ?
                                null :
                                publisher.getBookCollections()
                                        .stream()
                                        .map(BaseEntity::getUuid)
                                        .collect(Collectors.toSet()))
                .build();
    }

    Publisher toEntity(PublisherDTO publisherDTO);
}
