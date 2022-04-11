package com.adventureforge.gameservice.mappers;

import com.adventureforge.gameservice.dto.BookDTO;
import com.adventureforge.gameservice.entities.Author;
import com.adventureforge.gameservice.entities.BaseEntity;
import com.adventureforge.gameservice.entities.Book;
import com.adventureforge.gameservice.entities.BookCategory;
import com.adventureforge.gameservice.entities.BookCollection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = LocaleMapper.class)
public interface BookMapper {

    LocaleMapper LOCALE_MAPPER_INSTANCE = Mappers.getMapper(LocaleMapper.class);

    default BookDTO toDTO(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .uuid(book.getUuid())
                .title(book.getTitle())
                .subtitle(book.getSubtitle())
                .description(book.getDescription())
                .language(LOCALE_MAPPER_INSTANCE.localeToString(book.getLanguage()))
                .isbn(book.getIsbn())
                .authorsUuid(
                        book.getAuthors() == null ?
                                null :
                                book.getAuthors()
                                        .stream()
                                        .map(BaseEntity::getUuid)
                                        .collect(Collectors.toSet()))
                .collectionUuid(book.getBookCollection().getUuid())
                .publisherUuid(book.getBookCollection().getPublisher().getUuid())
                .rolePlayingGameUuid(book.getBookCollection().getEdition().getRolePlayingGame().getUuid())
                .editionUuid(book.getBookCollection().getEdition().getUuid())
                .category(book.getBookCategory().name())
                .build();
    }

    default Book toEntity(BookDTO bookDTO) {
        return Book.builder()
                .uuid(bookDTO.getUuid())
                .title(bookDTO.getTitle())
                .cover(bookDTO.getCover())
                .description(bookDTO.getDescription())
                .language(LOCALE_MAPPER_INSTANCE.stringToLocale(bookDTO.getLanguage()))
                .isbn(bookDTO.getIsbn())
                .bookCollection(BookCollection.builder().uuid(bookDTO.getCollectionUuid()).build())
                .bookCategory(BookCategory.valueOf(bookDTO.getCategory()))
                .authors(bookDTO.getAuthorsUuid()
                        .stream()
                        .map(uuid -> Author.builder().uuid(uuid).build())
                        .collect(Collectors.toSet())
                )
                .build();
    }

}
