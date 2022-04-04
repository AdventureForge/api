package com.adventureforge.gameservice.services;

import com.adventureforge.gameservice.entities.RolePlayingGame;
import com.adventureforge.gameservice.repositories.RolePlayingGameRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class RolePlayingGameService {

    private RolePlayingGameRepository rolePlayingGameRepository;
    private static final String UUID_PARAM = "uuid";

    public Page<RolePlayingGame> findAllPaginated(Pageable pageable) {
        return this.rolePlayingGameRepository.findAll(pageable);
    }
}
