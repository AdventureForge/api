package com.adventureforge.gameservice.mappers;

import com.adventureforge.gameservice.dto.BookCollectionDTO;
import com.adventureforge.gameservice.entities.BaseEntity;
import com.adventureforge.gameservice.entities.BookCollection;
import com.adventureforge.gameservice.entities.Edition;
import com.adventureforge.gameservice.entities.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookCollectionMapper {

    DateTimeMapper DATE_TIME_MAPPER_INSTANCE = Mappers.getMapper(DateTimeMapper.class);

    default BookCollectionDTO toDTO(BookCollection bookCollection) {
        return BookCollectionDTO.builder()
                .id(bookCollection.getId())
                .uuid(bookCollection.getUuid())
                .title(bookCollection.getTitle())
                .description(bookCollection.getDescription())
                .editionUuid(bookCollection.getEdition().getUuid())
                .publisherUuid(bookCollection.getPublisher().getUuid())
                .booksUuids(bookCollection.getBooks()
                        .stream()
                        .map(BaseEntity::getUuid)
                        .collect(Collectors.toSet()))
                .dateCreated(DATE_TIME_MAPPER_INSTANCE.toFormattedString(bookCollection.getDateCreated()))
                .userCreated(bookCollection.getUserCreated())
                .lastModified(DATE_TIME_MAPPER_INSTANCE.toFormattedString(bookCollection.getLastModified()))
                .userModified(bookCollection.getUserModified())
                .build();
    }

    default BookCollection toEntity(BookCollectionDTO bookCollectionDTO) {
        return BookCollection.builder()
                .title(bookCollectionDTO.getTitle())
                .description(bookCollectionDTO.getDescription())
                .edition(Edition.builder().uuid(bookCollectionDTO.getEditionUuid()).build())
                .publisher(Publisher.builder().uuid(bookCollectionDTO.getEditionUuid()).build())
                .build();
    }
}
