package com.adventureforge.gameservice.mappers;

import com.adventureforge.gameservice.dto.BookDTO;
import com.adventureforge.gameservice.entities.Book;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookMapper {

    default BookDTO toDTO(Book book) {
        return BookDTO.builder()
                .bookUuid(book.getUuid())
                .title(book.getTitle())
                .subtitle(book.getSubtitle())
                .language(book.getLanguage())
                .isbn(book.getIsbn())
                .authorsUuid(
                        book.getAuthorBooks() == null ?
                                null :
                                book.getAuthorBooks()
                                        .stream()
                                        .map(authorBook -> authorBook.getAuthor().getUuid())
                                        .collect(Collectors.toSet()))
                .collectionUuid(book.getBookCollection().getUuid())
                .publisherUuid(book.getPublisher().getUuid())
                .rolePlayingGameUuid(book.getBookCollection().getEdition().getRolePlayingGame().getUuid())
                .editionUuid(book.getBookCollection().getEdition().getUuid())
                .category(book.getBookCategory().name())
                .build();
    }

    Book toEntity(BookDTO bookDTO);

}
