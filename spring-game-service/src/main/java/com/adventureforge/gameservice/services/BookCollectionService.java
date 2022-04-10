package com.adventureforge.gameservice.services;

import com.adventureforge.gameservice.entities.BookCollection;
import com.adventureforge.gameservice.exceptions.EntityNotFoundException;
import com.adventureforge.gameservice.repositories.BookCollectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class BookCollectionService {

    private BookCollectionRepository bookCollectionRepository;
    private static final String UUID_PARAM = "uuid";

    public BookCollection findByUuid(UUID uuid) {
        return this.bookCollectionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException(BookCollection.class, UUID_PARAM, uuid));
    }
}
