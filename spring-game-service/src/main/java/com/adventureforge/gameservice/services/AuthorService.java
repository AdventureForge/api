package com.adventureforge.gameservice.services;

import com.adventureforge.gameservice.entities.Author;
import com.adventureforge.gameservice.exceptions.EntityNotFoundException;
import com.adventureforge.gameservice.repositories.AuthorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private static final String LASTNAME_PARAM = "lastname";
    private static final String FIRSTNAME_PARAM = "firstname";
    private static final String UUID_PARAM = "uuid";

    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    public Page<Author> findAllPaginated(Pageable pageable) {
        return this.authorRepository.findAll(pageable);
    }

    public Page<Author> searchByNamePaginated(String name, Pageable pageable) {
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher(FIRSTNAME_PARAM, ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher(LASTNAME_PARAM, ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Author authorToSearch = Author.builder().firstname(name).lastname(name).build();

        Page<Author> authorsFound = this.authorRepository.findAll(
                Example.of(authorToSearch, exampleMatcher),
                pageable
        );

        if (!authorsFound.isEmpty()) {
            return authorsFound;
        } else {
            throw new EntityNotFoundException(Author.class, "name", name);
        }
    }

    public Author findById(UUID uuid) {
        return this.authorRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException(Author.class, UUID_PARAM, uuid));
    }

    public Author save(Author author) {
        author.setUuid(UUID.randomUUID());
        return this.authorRepository.save(author);
    }

    public Author update(UUID uuid, Author authorToUpdate) {
        return this.authorRepository.findByUuid(uuid)
                .map(authorFromDb -> this.authorRepository.save(
                        Author.builder()
                                .id(authorFromDb.getId())
                                .uuid(authorFromDb.getUuid())
                                .firstname(authorToUpdate.getFirstname())
                                .lastname(authorToUpdate.getLastname())
                                .build()))
                .orElseThrow(() -> new EntityNotFoundException(
                        Author.class, UUID_PARAM, uuid, "authorToUpdate", authorToUpdate)
                );
    }

    public void delete(UUID uuid) {
        Author authorToDelete = this.authorRepository
                .findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException(Author.class, UUID_PARAM, uuid));

        this.authorRepository.deleteById(authorToDelete.getId());

    }
}
