package com.adventureforge.gameservice.controllers;

import com.adventureforge.gameservice.controllers.wrappers.ResponseWrapper;
import com.adventureforge.gameservice.dto.RolePlayingGameDTO;
import com.adventureforge.gameservice.dto.View;
import com.adventureforge.gameservice.mappers.RolePlayingGameMapper;
import com.adventureforge.gameservice.services.RolePlayingGameService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.adventureforge.gameservice.controllers.wrappers.ResponseWrapper.wrap;
import static com.adventureforge.gameservice.controllers.wrappers.ResponseWrapper.wrapPageToList;

@Tag(name = "RolePlayingGames")
@AllArgsConstructor
@RestController
@RequestMapping("/roleplayinggames")
public class RolePlayingGameController {

    private RolePlayingGameService rolePlayingGameService;
    private RolePlayingGameMapper rolePlayingGameMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<List<RolePlayingGameDTO>> findAll(@ParameterObject Pageable pageable) {
        return wrapPageToList(
                this.rolePlayingGameService.findAllPaginated(pageable)
                        .map(rolePlayingGameMapper::toDTO)
        );
    }

    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<RolePlayingGameDTO> findByUuid(@PathVariable String uuid) {
        return wrap(
                this.rolePlayingGameMapper.toDTO(
                        this.rolePlayingGameService.findByUuid(
                                UUID.fromString(uuid)
                        )
                )
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<RolePlayingGameDTO> create(
            @RequestBody @Valid @JsonView(View.External.POST.class) RolePlayingGameDTO rolePlayingGameDTO) {
        return wrap(
                this.rolePlayingGameMapper.toDTO(
                        this.rolePlayingGameService.create(
                                this.rolePlayingGameMapper.toEntity(rolePlayingGameDTO)
                        )
                )
        );
    }

    @PutMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<RolePlayingGameDTO> update(
            @PathVariable("uuid") String uuid,
            @RequestBody @Valid @JsonView(View.External.PUT.class) RolePlayingGameDTO rolePlayingGameDTO) {
        return wrap(
                this.rolePlayingGameMapper.toDTO(
                        this.rolePlayingGameService.update(
                                UUID.fromString(uuid),
                                this.rolePlayingGameMapper.toEntity(rolePlayingGameDTO)
                        )
                )
        );
    }

    @DeleteMapping(path = "/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("uuid") String uuid) {
        this.rolePlayingGameService.deleteByUuid(UUID.fromString(uuid));
    }


}