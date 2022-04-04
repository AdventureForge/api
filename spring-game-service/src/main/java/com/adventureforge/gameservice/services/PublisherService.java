package com.adventureforge.gameservice.services;

import com.adventureforge.gameservice.entities.Publisher;
import com.adventureforge.gameservice.repositories.PublisherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class PublisherService {

    private PublisherRepository publisherRepository;

    public Page<Publisher> findAllPaginated(Pageable pageable) {
        return this.publisherRepository.findAll(pageable);
    }
}
