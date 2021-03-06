package com.adventureforge.gameservice.services;

import com.adventureforge.gameservice.entities.Edition;
import com.adventureforge.gameservice.entities.RolePlayingGame;
import com.adventureforge.gameservice.exceptions.EntityNotFoundException;
import com.adventureforge.gameservice.repositories.EditionRepository;
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
public class EditionService {

    private EditionRepository editionRepository;
    private RolePlayingGameService rolePlayingGameService;
    private static final String UUID_PARAM = "uuid";

    public Page<Edition> findAllPaginated(Pageable pageable) {
        return this.editionRepository.findAll(pageable);
    }

    public Page<Edition> findAllByRolePlayingGameUuidPaginated(Pageable pageable, UUID rpgUuid) {
        RolePlayingGame rpg = this.rolePlayingGameService.findByUuid(rpgUuid);
        return this.editionRepository.findAllByRolePlayingGame(rpg, pageable);
    }

    public Edition findByUuid(UUID uuid) {
        return this.editionRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException(Edition.class, UUID_PARAM, uuid));
    }

    public Edition create(Edition edition) {
        edition.setUuid(UUID.randomUUID());
        return this.editionRepository.save(edition);
    }

    public Edition createWithDependencies(Edition edition) {
        RolePlayingGame rolePlayingGame = this.rolePlayingGameService.findByUuid(edition.getRolePlayingGame().getUuid());
        edition.setRolePlayingGame(rolePlayingGame);
        return this.create(edition);
    }

    public Edition update(UUID uuid, Edition editionToUpdate) {
        Edition edition = this.editionRepository.findByUuid(uuid)
                .map(editionFromDb -> this.editionRepository.save(
                        Edition.builder()
                                .id(editionFromDb.getId())
                                .uuid(editionFromDb.getUuid())
                                .editionNumber(editionToUpdate.getEditionNumber())
                                .editionTitle(editionToUpdate.getEditionTitle())
                                .userCreated(editionFromDb.getUserCreated())
                                .dateCreated(editionFromDb.getDateCreated())
                                .lastModified(editionFromDb.getLastModified())
                                .userModified(editionFromDb.getUserModified())
                                .rolePlayingGame(
                                        this.rolePlayingGameService
                                                .findByUuid(editionToUpdate.getRolePlayingGame()
                                                        .getUuid()))
                                .build()
                )).orElseThrow(() -> new EntityNotFoundException(
                        Edition.class, UUID_PARAM, uuid, "editionToUpdate", editionToUpdate)
                );

        log.info(edition.toString());
        return edition;
    }

    public void deleteByUuid(UUID uuid) {
        Edition editionToDelete = this.findByUuid(uuid);
        this.editionRepository.deleteById(editionToDelete.getId());
    }

    public void deleteByListOfUuids(List<UUID> uuids) {
        uuids.forEach(this::deleteByUuid);
    }
}
