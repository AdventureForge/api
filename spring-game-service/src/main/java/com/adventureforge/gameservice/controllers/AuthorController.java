package com.adventureforge.gameservice.controllers;

import com.adventureforge.gameservice.controllers.wrappers.ErrorResponseWrapper;
import com.adventureforge.gameservice.dto.AuthorDTO;
import com.adventureforge.gameservice.dto.View;
import com.adventureforge.gameservice.mappers.AuthorMapper;
import com.adventureforge.gameservice.services.AuthorService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Tag(name = "Authors")
@AllArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;
    private AuthorMapper authorMapper;

    @Operation(summary = "Find all authors paginated", description = "return a list of all authors with the pagination information", responses = {
            @ApiResponse(responseCode = "200", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(
                            schema = @Schema(implementation = AuthorDTO.class)))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorResponseWrapper.class)))
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(value = View.GET.class)
    public Page<AuthorDTO> findAllPaginated(Pageable pageable) {
        return this.authorService.findAllPaginated(pageable)
                .map(authorMapper::toDTO);
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(value = View.GET.class)
    public List<AuthorDTO> findAll() {
        return this.authorService.findAllPaginated(PageRequest.of(0, 5))
                .getContent()
                .stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    @GetMapping(path = "/search/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(value = View.GET.class)
    public List<AuthorDTO> searchByNamePaginated(
            Pageable pageable,
            @PathVariable("name") String name) {

        return this.authorService.searchByNamePaginated(name, pageable)
                .stream()
                .map(authorMapper::toDTO)
                .toList();
    }

    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(value = View.GET.class)
    public AuthorDTO findByUuid(@PathVariable("uuid") String uuid) {
        return this.authorMapper.toDTO(this.authorService.findById(UUID.fromString(uuid)));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(value = View.GET.class)
    public AuthorDTO create(@RequestBody @Valid @JsonView(value = View.POST.class) AuthorDTO authorDTO) {
        return this.authorMapper.toDTO(
                this.authorService.save(
                        this.authorMapper.toEntity(authorDTO)
                )
        );
    }


    @PutMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(value = View.GET.class)
    public AuthorDTO update(
            @PathVariable("uuid") @Valid String uuid,
            @RequestBody @Valid @JsonView(value = View.PUT.class) AuthorDTO authorDTO) {
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
