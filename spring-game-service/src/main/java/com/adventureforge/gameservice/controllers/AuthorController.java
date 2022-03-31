package com.adventureforge.gameservice.controllers;

import com.adventureforge.gameservice.dtos.AuthorDTO;
import com.adventureforge.gameservice.mappers.AuthorMapper;
import com.adventureforge.gameservice.services.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;
    private AuthorMapper authorMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AuthorDTO> findAllPaginated(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size) {

        return this.authorService.findAllPaginated(page, size)
                .stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    @GetMapping(path = "/search/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AuthorDTO> searchByNamePaginated(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "12") Integer size,
            @PathVariable("name") String name) {

        return this.authorService.searchByNamePaginated(name, page, size)
                .stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorDTO findByUuid(@PathVariable("uuid") String uuid) {
        return this.authorMapper.toDTO(this.authorService.findById(UUID.fromString(uuid)));
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorDTO create(@RequestBody AuthorDTO authorDTO) {
        return this.authorMapper.toDTO(
                this.authorService.save(
                        this.authorMapper.toEntity(authorDTO)
                )
        );
    }

    @PutMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthorDTO update(@PathVariable("uuid") String uuid, @RequestBody AuthorDTO authorDTO) {
        return this.authorMapper.toDTO(
                this.authorService.update(
                        UUID.fromString(uuid),
                        this.authorMapper.toEntity(authorDTO)
                )
        );
    }

    @DeleteMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("uuid") String uuid) {
        this.authorService.delete(UUID.fromString(uuid));
    }
}
