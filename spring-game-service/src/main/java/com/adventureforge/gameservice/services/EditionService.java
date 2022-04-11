package com.adventureforge.gameservice.services;

import com.adventureforge.gameservice.entities.Edition;
import com.adventureforge.gameservice.entities.RolePlayingGame;
import com.adventureforge.gameservice.exceptions.EntityNotFoundException;
import com.adventureforge.gameservice.repositories.EditionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class EditionService {

    private EditionRepository editionRepository;
    private RolePlayingGameService rolePlayingGameService;
    private static final String UUID_PARAM = "uuid";

    public Page<Edition> findAllAPaginated(Pageable pageable) {
        return this.editionRepository.findAll(pageable);
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
        return this.editionRepository.findByUuid(uuid)
                .map(editionFromDb -> this.editionRepository.save(
                        Edition.builder()
                                .id(editionFromDb.getId())
                                .uuid(editionFromDb.getUuid())
                                .editionNumber(editionToUpdate.getEditionNumber())
                                .editionTitle(editionToUpdate.getEditionTitle())
                                .rolePlayingGame(
                                        this.rolePlayingGameService
                                                .findByUuid(editionToUpdate.getRolePlayingGame()
                                                        .getUuid()))
                                .build()
                )).orElseThrow(() -> new EntityNotFoundException(Edition.class, UUID_PARAM, uuid));
    }

    public void delete(UUID uuid) {
        Edition editionToDelete = this.findByUuid(uuid);
        this.editionRepository.deleteById(editionToDelete.getId());
    }
}
