package com.adventureforge.adventureservice.dataloader;

import com.adventureforge.adventureservice.entities.Adventure;
import com.adventureforge.adventureservice.entities.Scene;
import com.adventureforge.adventureservice.repositories.AdventureRepository;
import com.adventureforge.adventureservice.repositories.SceneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Transactional
@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private SceneRepository sceneRepository;
    private AdventureRepository adventureRepository;

    @Override
    public void run(String... args) throws Exception {

        Adventure adventure = Adventure
                .builder()
                .uuid(UUID.randomUUID())
                .title("Aventure 1")
                .build();

        Scene scene1 = Scene.builder()
                .uuid(UUID.randomUUID())
                .title("Scene 1")
                .adventure(adventure)
                .build();

        Scene scene2 = Scene.builder()
                .uuid(UUID.randomUUID())
                .title("Scene 2")
                .adventure(adventure)
                .build();

        Scene scene3 = Scene.builder()
                .uuid(UUID.randomUUID())
                .title("Scene 3")
                .adventure(adventure)
                .build();

        Scene scene4 = Scene.builder()
                .uuid(UUID.randomUUID())
                .title("Scene 4")
                .adventure(adventure)
                .build();

        Scene scene5 = Scene.builder()
                .uuid(UUID.randomUUID())
                .title("Scene 5")
                .adventure(adventure)
                .build();

        scene1.setNextScenes(Set.of(scene2, scene3));
        scene2.setPreviousScenes(Set.of(scene1));
        scene2.setNextScenes(Set.of(scene4));
        scene3.setPreviousScenes(Set.of(scene1));
        scene3.setNextScenes(Set.of(scene4));
        scene4.setPreviousScenes(Set.of(scene2, scene3));
        scene4.setNextScenes(Set.of(scene5));

        this.adventureRepository.save(adventure);
        this.sceneRepository.saveAll(List.of(scene1, scene2, scene3, scene4, scene5));
    }
}
