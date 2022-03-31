package com.adventureforge.gameservice.mappers;

import com.adventureforge.gameservice.dtos.AuthorDTO;
import com.adventureforge.gameservice.entities.Author;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    default AuthorDTO toDTO(Author author) {
        return AuthorDTO.builder()
                .uuid(author.getUuid())
                .firstName(author.getFirstName())
                .lastname(author.getLastname())
                .pseudo(author.getPseudo())
                .booksUuids(
                        author.getAuthorBooks()
                                .stream()
                                .map(authorBook -> authorBook.getAuthor().getUuid())
                                .collect(Collectors.toSet()))
                .build();
    }

    Author toEntity(AuthorDTO authorDTO);
}
