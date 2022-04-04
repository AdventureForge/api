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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
