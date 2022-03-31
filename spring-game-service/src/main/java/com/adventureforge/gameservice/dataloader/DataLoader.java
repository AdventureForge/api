package com.adventureforge.gameservice.dataloader;

import com.adventureforge.gameservice.entities.*;
import com.adventureforge.gameservice.repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Transactional
@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private PublisherRepository publisherRepository;
    private BookRepository bookRepository;
    private BookCollectionRepository bookCollectionRepository;
    private AuthorRepository authorRepository;
    private RolePlayingGameRepository rolePlayingGameRepository;
    private EditionRepository editionRepository;
    private AuthorBookRepository authorBookRepository;

    @Override
    public void run(String... args) throws Exception {


        Publisher publisher = Publisher.builder()
                .uuid(UUID.randomUUID())
                .name("Black Book Edition")
                .build();


        Edition edition = Edition.builder()
                .uuid(UUID.randomUUID())
                .editionNumber(1)
                .editionTitle("1ère édition")
                .build();

        RolePlayingGame rolePlayingGame = RolePlayingGame.builder()
                .uuid(UUID.randomUUID())
                .title("Pavillon Noir")
                .editions(List.of(edition))
                .build();

        edition.setRolePlayingGame(rolePlayingGame);

        BookCollection bookCollection = BookCollection.builder()
                .uuid(UUID.randomUUID())
                .title("default")
                .edition(edition)
                .build();

        Author author = Author.builder()
                .uuid(UUID.randomUUID())
                .firstname("John")
                .lastname("Doe")
                .build();

        Book book = Book.builder()
                .uuid(UUID.randomUUID())
                .title("livre 1")
                .bookCategory(BookCategory.ADVENTURE)
                .bookCollection(bookCollection)
                .publisher(publisher)
                .build();

        AuthorBook authorBook = AuthorBook.builder()
                .author(author)
                .book(book)
                .uuid(UUID.randomUUID())
                .writer(true)
                .illustrator(false)
                .authorBookKey(new AuthorBookKey(book.getId(), author.getId()))
                .build();

        author.setAuthorBooks(Set.of(authorBook));
        book.setAuthorBooks(Set.of(authorBook));

        this.publisherRepository.save(publisher);
        this.rolePlayingGameRepository.save(rolePlayingGame);
        this.editionRepository.save(edition);
        this.bookCollectionRepository.save(bookCollection);
        this.authorRepository.save(author);
        this.bookRepository.save(book);
        this.authorBookRepository.save(authorBook);

        log.info(author.getUuid().toString());

    }
}
