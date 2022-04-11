package com.adventureforge.gameservice.services;

import com.adventureforge.gameservice.entities.Edition;
import com.adventureforge.gameservice.entities.RolePlayingGame;
import com.adventureforge.gameservice.exceptions.EntityNotFoundException;
import com.adventureforge.gameservice.repositories.EditionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class EditionService {

    private EditionRepository editionRepository;
    private RolePlayingGameService rolePlayingGameService;
    private static final String UUID_PARAM = "uuid";

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
}
