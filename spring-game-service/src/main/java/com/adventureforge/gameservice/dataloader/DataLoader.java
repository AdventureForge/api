package com.adventureforge.gameservice.dataloader;

import com.adventureforge.gameservice.entities.Author;
import com.adventureforge.gameservice.entities.Book;
import com.adventureforge.gameservice.entities.BookCategory;
import com.adventureforge.gameservice.entities.BookCollection;
import com.adventureforge.gameservice.entities.Edition;
import com.adventureforge.gameservice.entities.Publisher;
import com.adventureforge.gameservice.entities.RolePlayingGame;
import com.adventureforge.gameservice.repositories.AuthorRepository;
import com.adventureforge.gameservice.repositories.BookCollectionRepository;
import com.adventureforge.gameservice.repositories.BookRepository;
import com.adventureforge.gameservice.repositories.EditionRepository;
import com.adventureforge.gameservice.repositories.PublisherRepository;
import com.adventureforge.gameservice.repositories.RolePlayingGameRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
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
                .publisher(publisher)
                .build();

        BookCollection bookCollection2 = BookCollection.builder()
                .uuid(UUID.randomUUID())
                .title(BookCollection.DEFAULT_COLLECTION)
                .edition(edition2)
                .publisher(publisher)
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
                .isbn("2-915847-00-2")
                .language(Locale.FRENCH)
                .build();

        Book book1 = Book.builder()
                .uuid(UUID.randomUUID())
                .title("La Révolte")
                .bookCategory(BookCategory.CORE_RULEBOOK)
                .bookCollection(bookCollection2)
                .isbn("978-2-36328-252-1")
                .language(Locale.CANADA_FRENCH)
                .build();

        Book book2 = Book.builder()
                .uuid(UUID.randomUUID())
                .title("À Feu et à Sang")
                .bookCategory(BookCategory.CORE_RULEBOOK)
                .bookCollection(bookCollection2)
                .isbn("978-2-36328-254-5")
                .language(Locale.FRENCH)
                .build();

        book0.setAuthors(Set.of(author));
        book1.setAuthors(Set.of(author));
        book2.setAuthors(Set.of(author));
        author.setBooks(Set.of(book0, book1, book2));

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

        log.info(author.getUuid().toString());

    }
}
