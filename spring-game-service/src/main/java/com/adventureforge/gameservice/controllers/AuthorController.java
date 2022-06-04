package com.adventureforge.gameservice.controllers;

import com.adventureforge.gameservice.controllers.wrappers.ResponseWrapper;
import com.adventureforge.gameservice.dto.AuthorDTO;
import com.adventureforge.gameservice.dto.View;
import com.adventureforge.gameservice.mappers.AuthorMapper;
import com.adventureforge.gameservice.services.AuthorService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.adventureforge.gameservice.controllers.wrappers.ResponseWrapper.wrap;
import static com.adventureforge.gameservice.controllers.wrappers.ResponseWrapper.wrapPageToList;

@Slf4j
@Tag(name = "Authors")
@AllArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;
    private AuthorMapper authorMapper;

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<List<AuthorDTO>> findAllPaginated(@ParameterObject Pageable pageable) {
        log.debug("Call findAllPaginated");
        return wrapPageToList(
                this.authorService.findAllPaginated(pageable)
                        .map(authorMapper::toDTO)
        );
    }

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(path = "/search/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<List<AuthorDTO>> searchByNamePaginated(
            @ParameterObject Pageable pageable,
            @PathVariable("name") String name) {

        return wrapPageToList(
                this.authorService.searchByNamePaginated(name, pageable)
                        .map(authorMapper::toDTO)
        );
    }

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<AuthorDTO> findByUuid(@PathVariable("uuid") String uuid) {
        return wrap(
                this.authorMapper.toDTO(
                        this.authorService
                                .findByUuid(UUID.fromString(uuid))
                )
        );
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<AuthorDTO> create(@RequestBody @Valid @JsonView(value = View.External.POST.class) AuthorDTO authorDTO) {
        return wrap(
                this.authorMapper.toDTO(
                        this.authorService.create(
                                this.authorMapper.toEntity(authorDTO)
                        )
                )
        );
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PutMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<AuthorDTO> update(
            @PathVariable("uuid") String uuid,
            @RequestBody @Valid @JsonView(value = View.External.PUT.class) AuthorDTO authorDTO) {
        return wrap(
                this.authorMapper.toDTO(
                        this.authorService.update(
                                UUID.fromString(uuid),
                                this.authorMapper.toEntity(authorDTO)
                        )
                )
        );
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping(value = "/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("uuid") String uuid) {
        this.authorService.deleteByUuid(UUID.fromString(uuid));
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteList(@RequestBody List<String> uuids) {
        List<UUID> uuidList = uuids
                .stream()
                .map(UUID::fromString)
                .toList();

        this.authorService.deleteByListOfUuids(uuidList);
    }
}
