package com.adventureforge.gameservice.mappers;

import com.adventureforge.gameservice.dto.BookCollectionDTO;
import com.adventureforge.gameservice.entities.BaseEntity;
import com.adventureforge.gameservice.entities.BookCollection;
import com.adventureforge.gameservice.entities.Edition;
import com.adventureforge.gameservice.entities.Publisher;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookCollectionMapper {

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
