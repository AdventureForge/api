package com.adventureforge.gameservice.services;

import com.adventureforge.gameservice.entities.Author;
import com.adventureforge.gameservice.exceptions.EntityNotFoundException;
import com.adventureforge.gameservice.repositories.AuthorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private static final String LASTNAME_PARAM = "lastname";
    private static final String FIRSTNAME_PARAM = "firstname";
    private static final String UUID_PARAM = "uuid";

    public List<Author> findAllPaginated(int page, int size) {

        return this.authorRepository
                .findAll(
                        PageRequest.of(
                                page,
                                size,
                                Sort
                                        .by(LASTNAME_PARAM)
                                        .ascending()))
                .stream()
                .toList();
    }

    public List<Author> searchByNamePaginated(String name, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(LASTNAME_PARAM).ascending());
        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAny()
                .withMatcher(FIRSTNAME_PARAM, ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher(LASTNAME_PARAM, ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Author authorToSearch = Author.builder().firstname(name).lastname(name).build();

        return this.authorRepository
                .findAll(Example.of(authorToSearch, exampleMatcher), pageable)
                .stream()
                .toList();
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
                .map(authorFromDb -> this.authorRepository.save(Author.builder()
                        .id(authorFromDb.getId())
                        .uuid(authorFromDb.getUuid())
                        .firstname(authorToUpdate.getFirstname())
                        .lastname(authorToUpdate.getLastname())
                        .build()))
                .orElseThrow(() -> new EntityNotFoundException(Author.class, UUID_PARAM, uuid, "authorToUpdate", authorToUpdate));
    }

    public void delete(UUID uuid) {
        Optional<Author> authorFromDb = this.authorRepository.findByUuid(uuid);
        if (authorFromDb.isPresent()) {
            this.authorRepository.delete(authorFromDb.get());
        } else {
            throw new EntityNotFoundException(Author.class, UUID_PARAM, uuid);
        }
    }
}
