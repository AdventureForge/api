package com.adventureforge.gameservice.controllers;

import com.adventureforge.gameservice.dtos.AuthorDTO;
import com.adventureforge.gameservice.mappers.AuthorMapper;
import com.adventureforge.gameservice.services.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;
    private AuthorMapper authorMapper;

    @GetMapping("/{uuid}")
    public AuthorDTO findByUuid(@PathVariable("uuid") String uuid) {
        return this.authorMapper.toDTO(this.authorService.findById(UUID.fromString(uuid)));
    }

    @PostMapping
    public AuthorDTO create(@RequestBody AuthorDTO authorDTO) {
        return this.authorMapper.toDTO(
                this.authorService.save(
                        this.authorMapper.toEntity(authorDTO)
                )
        );

    }
}
