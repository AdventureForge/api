package com.adventureforge.gameservice.controllers;

import com.adventureforge.gameservice.controllers.wrappers.ResponseWrapper;
import com.adventureforge.gameservice.dto.EditionDTO;
import com.adventureforge.gameservice.dto.View;
import com.adventureforge.gameservice.mappers.EditionMapper;
import com.adventureforge.gameservice.services.EditionService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
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

@Tag(name = "Editions")
@AllArgsConstructor
@RestController
@RequestMapping("/editions")
public class EditionController {

    private EditionService editionService;
    private EditionMapper editionMapper;

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<List<EditionDTO>> findAllPaginated(@ParameterObject Pageable pageable) {
        return wrapPageToList(
                this.editionService.findAllAPaginated(pageable)
                        .map(editionMapper::toDTO)
        );
    }

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<EditionDTO> findByUuid(@PathVariable("uuid") String uuid) {
        return wrap(
                this.editionMapper.toDTO(
                        this.editionService.findByUuid(UUID.fromString(uuid))
                )
        );
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<EditionDTO> create(@RequestBody @Valid @JsonView(View.External.POST.class) EditionDTO editionDTO) {
        return wrap(
                this.editionMapper.toDTO(
                        this.editionService.createWithDependencies(
                                this.editionMapper.toEntity(editionDTO)
                        )
                )
        );
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PutMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<EditionDTO> update(
            @PathVariable("uuid") String uuid,
            @RequestBody @Valid @JsonView(View.External.PUT.class) EditionDTO editionDTO) {
        return wrap(
                this.editionMapper.toDTO(
                        this.editionService.update(
                                UUID.fromString(uuid),
                                this.editionMapper.toEntity(
                                        editionDTO
                                )
                        )
                )
        );
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("/{uuid}")
    public void deleteByUuid(@PathVariable("uuid") String uuid) {
        this.editionService.deleteByUuid(UUID.fromString(uuid));
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteList(@RequestBody List<String> uuids) {
        List<UUID> uuidList = uuids
                .stream()
                .map(UUID::fromString)
                .toList();

        this.editionService.deleteByListOfUuids(uuidList);
    }
}
