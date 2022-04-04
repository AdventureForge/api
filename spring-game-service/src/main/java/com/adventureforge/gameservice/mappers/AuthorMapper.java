package com.adventureforge.gameservice.mappers;

import com.adventureforge.gameservice.dto.AuthorDTO;
import com.adventureforge.gameservice.entities.Author;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    default AuthorDTO toDTO(Author author) {
        return AuthorDTO.builder()
                .uuid(author.getUuid())
                .firstname(author.getFirstname())
                .lastname(author.getLastname())
                .booksUuids(
                        author.getAuthorBooks() == null ?
                                null :
                                author.getAuthorBooks()
                                        .stream()
                                        .map(authorBook -> authorBook.getBook().getUuid())
                                        .collect(Collectors.toSet()))
                .build();
    }

    Author toEntity(AuthorDTO authorDTO);
}
