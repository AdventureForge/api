package com.adventureforge.gameservice.services;

import com.adventureforge.gameservice.entities.RolePlayingGame;
import com.adventureforge.gameservice.exceptions.EntityNotFoundException;
import com.adventureforge.gameservice.repositories.RolePlayingGameRepository;
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
public class RolePlayingGameService {

    private RolePlayingGameRepository rolePlayingGameRepository;
    private static final String UUID_PARAM = "uuid";

    public Page<RolePlayingGame> findAllPaginated(Pageable pageable) {
        return this.rolePlayingGameRepository.findAll(pageable);
    }

    public RolePlayingGame findByUuid(UUID uuid) {
        return this.rolePlayingGameRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException(
                                RolePlayingGame.class, UUID_PARAM, uuid
                        )
                );
    }

    public RolePlayingGame create(RolePlayingGame rolePlayingGame) {
        rolePlayingGame.setUuid(UUID.randomUUID());
        return this.rolePlayingGameRepository.save(rolePlayingGame);
    }

    public RolePlayingGame update(UUID uuid, RolePlayingGame rolePlayingGameToUpdate) {
        return this.rolePlayingGameRepository.findByUuid(uuid)
                .map(rolePlayingGameFromDb -> this.rolePlayingGameRepository.save(
                        RolePlayingGame.builder()
                                .id(rolePlayingGameFromDb.getId())
                                .uuid(rolePlayingGameFromDb.getUuid())
                                .title(rolePlayingGameToUpdate.getTitle())
                                .subtitle(rolePlayingGameToUpdate.getSubtitle())
                                .pictureUrl(rolePlayingGameToUpdate.getPictureUrl())
                                .websiteUrl(rolePlayingGameToUpdate.getWebsiteUrl())
                                .build()))
                .orElseThrow(() -> new EntityNotFoundException(
                                RolePlayingGame.class, UUID_PARAM, uuid
                        )
                );
    }

    public void deleteByUuid(UUID uuid) {
        RolePlayingGame rolePlayingGameToDelete = this.findByUuid(uuid);
        this.rolePlayingGameRepository.deleteById(rolePlayingGameToDelete.getId());
    }

    public void deleteByListOfUuids(List<UUID> uuids) {
        uuids.forEach(this::deleteByUuid);
    }
}
