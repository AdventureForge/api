package com.adventureforge.gameservice.controllers;

import com.adventureforge.gameservice.controllers.wrappers.ResponseWrapper;
import com.adventureforge.gameservice.dto.AuthorDTO;
import com.adventureforge.gameservice.dto.View;
import com.adventureforge.gameservice.mappers.AuthorMapper;
import com.adventureforge.gameservice.services.AuthorService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static com.adventureforge.gameservice.controllers.wrappers.ResponseWrapper.wrap;

@Tag(name = "Authors")
@AllArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;
    private AuthorMapper authorMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<Page<AuthorDTO>> findAllPaginated(@ParameterObject Pageable pageable) {
        return wrap(this.authorService.findAllPaginated(pageable)
                .map(authorMapper::toDTO));
    }

    @GetMapping(path = "/search/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<Page<AuthorDTO>> searchByNamePaginated(
            @ParameterObject Pageable pageable,
            @PathVariable("name") String name) {

        return wrap(this.authorService.searchByNamePaginated(name, pageable)
                .map(authorMapper::toDTO));
    }

    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<AuthorDTO> findByUuid(@PathVariable("uuid") String uuid) {
        return wrap(this.authorMapper.toDTO(
                this.authorService
                        .findById(UUID.fromString(uuid))
        ));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<AuthorDTO> create(@RequestBody @Valid @JsonView(value = View.External.POST.class) AuthorDTO authorDTO) {
        return wrap(this.authorMapper.toDTO(
                this.authorService.save(
                        this.authorMapper.toEntity(authorDTO)
                )
        ));
    }

    @PutMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<AuthorDTO> update(
            @PathVariable("uuid") @Valid String uuid,
            @RequestBody @Valid @JsonView(value = View.External.PUT.class) AuthorDTO authorDTO) {
        return wrap(this.authorMapper.toDTO(
                this.authorService.update(
                        UUID.fromString(uuid),
                        this.authorMapper.toEntity(authorDTO)
                )
        ));
    }

    @DeleteMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("uuid") String uuid) {
        this.authorService.delete(UUID.fromString(uuid));
    }
}
