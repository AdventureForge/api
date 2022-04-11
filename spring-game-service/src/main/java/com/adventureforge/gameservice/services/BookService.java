package com.adventureforge.gameservice.services;

import com.adventureforge.gameservice.entities.Author;
import com.adventureforge.gameservice.entities.Book;
import com.adventureforge.gameservice.entities.BookCollection;
import com.adventureforge.gameservice.exceptions.EntityNotFoundException;
import com.adventureforge.gameservice.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookService {

    private BookRepository bookRepository;
    private PublisherService publisherService;
    private BookCollectionService bookCollectionService;
    private AuthorService authorService;
    private static final String UUID_PARAM = "uuid";

    public Page<Book> findAll(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    public Book findByUuid(UUID uuid) {
        return this.bookRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException(Book.class, UUID_PARAM, uuid));
    }

    public Book create(Book book) {
        book.setUuid(UUID.randomUUID());
        return this.bookRepository.save(book);
    }

    public Book createBookWithDependencies(Book book) {

        BookCollection bookCollection = this.bookCollectionService.findByUuid(book.getBookCollection().getUuid());
        Set<Author> authors = book.getAuthors()
                .stream()
                .map(author -> this.authorService.findByUuid(author.getUuid()))
                .collect(Collectors.toSet());
        book.setBookCollection(bookCollection);
        book.setAuthors(authors);

        return this.create(book);
    }

    public Book update(UUID uuid, Book bookToUpdate) {
        return this.bookRepository.findByUuid(uuid)
                .map(bookFromDb -> this.bookRepository.save(
                        Book.builder()
                                .id(bookFromDb.getId())
                                .uuid(bookFromDb.getUuid())
                                .title(bookToUpdate.getTitle())
                                .subtitle(bookToUpdate.getSubtitle())
                                .cover(bookToUpdate.getCover())
                                .description(bookToUpdate.getDescription())
                                .language(bookToUpdate.getLanguage())
                                .isbn(bookToUpdate.getIsbn())
                                .bookCategory(bookToUpdate.getBookCategory())
                                .build()
                ))
                .orElseThrow(() -> new EntityNotFoundException(Book.class, UUID_PARAM, uuid));
    }

    public void delete(UUID uuid) {
        Book bookToDelete = this.findByUuid(uuid);
        this.bookRepository.deleteById(bookToDelete.getId());
    }
}
