package com.adventureforge.adventureservice.services;

import com.adventureforge.adventureservice.entities.Scene;
import com.adventureforge.adventureservice.repositories.SceneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SceneService {

    private SceneRepository sceneRepository;

    public List<Scene> findAll() {
        return this.sceneRepository.findAll();
    }
}
