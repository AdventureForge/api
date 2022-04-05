package com.adventureforge.adventureservice.controllers;

import com.adventureforge.adventureservice.entities.Scene;
import com.adventureforge.adventureservice.services.SceneService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Scenes")
@AllArgsConstructor
@RestController
@RequestMapping("scenes")
public class SceneController {

    private SceneService sceneService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Scene> findAll() {
        return this.sceneService.findAll();
    }
}
