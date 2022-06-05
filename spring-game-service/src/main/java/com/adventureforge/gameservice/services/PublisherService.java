package com.adventureforge.gameservice.services;

import com.adventureforge.gameservice.entities.Publisher;
import com.adventureforge.gameservice.exceptions.EntityNotFoundException;
import com.adventureforge.gameservice.repositories.PublisherRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class PublisherService {

    private PublisherRepository publisherRepository;
    private static final String UUID_PARAM = "uuid";

    public Page<Publisher> findAllPaginated(Pageable pageable) {
        return this.publisherRepository.findAll(pageable);
    }

    public Publisher findByUuid(UUID uuid) {
        return this.publisherRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException(Publisher.class, UUID_PARAM, uuid));
    }

    public Publisher create(Publisher publisher) {
        publisher.setUuid(UUID.randomUUID());
        return this.publisherRepository.save(publisher);
    }

    public Publisher update(UUID uuid, Publisher publisherToUpdate) {
        return this.publisherRepository.findByUuid(uuid)
                .map(publisherFromDb -> this.publisherRepository.save(
                        Publisher.builder()
                                .id(publisherFromDb.getId())
                                .uuid(publisherFromDb.getUuid())
                                .name(publisherToUpdate.getName())
                                .description(publisherToUpdate.getDescription())
                                .websiteUrl(publisherToUpdate.getWebsiteUrl())
                                .logo(publisherToUpdate.getLogo())
                                .userCreated(publisherFromDb.getUserCreated())
                                .dateCreated(publisherFromDb.getDateCreated())
                                .lastModified(publisherFromDb.getLastModified())
                                .userModified(publisherFromDb.getUserModified())
                                .build()))
                .orElseThrow(() -> new EntityNotFoundException(
                        Publisher.class, UUID_PARAM, uuid, "publisherToUpdate", publisherToUpdate)
                );
    }

    public void deleteByUuid(UUID uuid) {
        Publisher publisherToDelete = this.findByUuid(uuid);
        this.publisherRepository.deleteById(publisherToDelete.getId());
    }

    public void deleteByListOfUuids(List<UUID> uuids) {
        uuids.forEach(this::deleteByUuid);
    }
}
