package com.adventureforge.gameservice.services;

import com.adventureforge.gameservice.entities.Author;
import com.adventureforge.gameservice.repositories.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public Author findById(UUID uuid) {
        return this.authorRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Author not found with id"));
    }

    public Author save(Author author) {
        return this.authorRepository.save(author);
    }
}
