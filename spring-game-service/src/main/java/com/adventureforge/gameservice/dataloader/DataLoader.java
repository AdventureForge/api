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
                .websiteUrl("https://www.black-book-editions.fr/")
                .build();


        Edition edition1 = Edition.builder()
                .uuid(UUID.randomUUID())
                .editionNumber(1)
                .editionTitle("1ère édition")
                .build();

        Edition edition2 = Edition.builder()
                .uuid(UUID.randomUUID())
                .editionNumber(2)
                .editionTitle("2ème édition")
                .build();

        RolePlayingGame rolePlayingGame = RolePlayingGame.builder()
                .uuid(UUID.randomUUID())
                .title("Pavillon Noir")
                .editions(List.of(edition1, edition2))
                .websiteUrl("https://www.black-book-editions.fr/catalogue.php?id=7")
                .build();

        edition1.setRolePlayingGame(rolePlayingGame);
        edition2.setRolePlayingGame(rolePlayingGame);

        BookCollection bookCollection1 = BookCollection.builder()
                .uuid(UUID.randomUUID())
                .title(BookCollection.DEFAULT_COLLECTION)
                .edition(edition1)
                .build();

        BookCollection bookCollection2 = BookCollection.builder()
                .uuid(UUID.randomUUID())
                .title(BookCollection.DEFAULT_COLLECTION)
                .edition(edition2)
                .build();

        Author author = Author.builder()
                .uuid(UUID.randomUUID())
                .firstname("Renaud")
                .lastname("Maroy")
                .build();

        Book book0 = Book.builder()
                .uuid(UUID.randomUUID())
                .title("La Révolte")
                .bookCategory(BookCategory.CORE_RULEBOOK)
                .bookCollection(bookCollection1)
                .publisher(publisher)
                .isbn("2-915847-00-2")
                .language("french")
                .build();

        Book book1 = Book.builder()
                .uuid(UUID.randomUUID())
                .title("La Révolte")
                .bookCategory(BookCategory.CORE_RULEBOOK)
                .bookCollection(bookCollection2)
                .publisher(publisher)
                .isbn("978-2-36328-252-1")
                .language("french")
                .build();

        Book book2 = Book.builder()
                .uuid(UUID.randomUUID())
                .title("À Feu et à Sang")
                .bookCategory(BookCategory.CORE_RULEBOOK)
                .bookCollection(bookCollection2)
                .publisher(publisher)
                .isbn("978-2-36328-254-5")
                .language("french")
                .build();

        AuthorBook authorBook0 = AuthorBook.builder()
                .author(author)
                .book(book0)
                .uuid(UUID.randomUUID())
                .writer(true)
                .illustrator(false)
                .authorBookKey(new AuthorBookKey(book0.getId(), author.getId()))
                .build();

        AuthorBook authorBook1 = AuthorBook.builder()
                .author(author)
                .book(book1)
                .uuid(UUID.randomUUID())
                .writer(true)
                .illustrator(false)
                .authorBookKey(new AuthorBookKey(book1.getId(), author.getId()))
                .build();

        AuthorBook authorBook2 = AuthorBook.builder()
                .author(author)
                .book(book2)
                .uuid(UUID.randomUUID())
                .writer(true)
                .illustrator(false)
                .authorBookKey(new AuthorBookKey(book2.getId(), author.getId()))
                .build();

        author.setAuthorBooks(Set.of(authorBook0, authorBook1, authorBook2));
        book0.setAuthorBooks(Set.of(authorBook0));
        book1.setAuthorBooks(Set.of(authorBook1));
        book2.setAuthorBooks(Set.of(authorBook2));

        this.publisherRepository.save(publisher);
        this.rolePlayingGameRepository.save(rolePlayingGame);
        this.editionRepository.save(edition1);
        this.editionRepository.save(edition2);
        this.bookCollectionRepository.save(bookCollection1);
        this.bookCollectionRepository.save(bookCollection2);
        this.authorRepository.save(author);
        this.bookRepository.save(book0);
        this.bookRepository.save(book1);
        this.bookRepository.save(book2);
        this.authorBookRepository.save(authorBook0);
        this.authorBookRepository.save(authorBook1);
        this.authorBookRepository.save(authorBook2);

        log.info(author.getUuid().toString());

    }
}
