package com.adventureforge.gameservice.mappers;

import com.adventureforge.gameservice.dto.AuthorDTO;
import com.adventureforge.gameservice.entities.Author;
import com.adventureforge.gameservice.entities.BaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    DateTimeMapper DATE_TIME_MAPPER_INSTANCE = Mappers.getMapper(DateTimeMapper.class);

    default AuthorDTO toDTO(Author author) {
        return AuthorDTO.builder()
                .id(author.getId())
                .uuid(author.getUuid())
                .firstname(author.getFirstname())
                .lastname(author.getLastname())
                .booksUuids(
                        author.getBooks() == null ?
                                null :
                                author.getBooks()
                                        .stream()
                                        .map(BaseEntity::getUuid)
                                        .collect(Collectors.toSet()))
                .dateCreated(DATE_TIME_MAPPER_INSTANCE.toFormattedString(author.getDateCreated()))
                .userCreated(author.getUserCreated())
                .lastModified(DATE_TIME_MAPPER_INSTANCE.toFormattedString(author.getLastModified()))
                .userModified(author.getUserModified())
                .build();
    }

    Author toEntity(AuthorDTO authorDTO);
}
