package com.adventureforge.gameservice.controllers;

import com.adventureforge.gameservice.controllers.wrappers.ResponseWrapper;
import com.adventureforge.gameservice.dto.BookCollectionDTO;
import com.adventureforge.gameservice.dto.View;
import com.adventureforge.gameservice.mappers.BookCollectionMapper;
import com.adventureforge.gameservice.services.BookCollectionService;
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

@Tag(name = "Collections")
@AllArgsConstructor
@RestController
@RequestMapping("/collections")
public class BookCollectionController {

    private BookCollectionService bookCollectionService;
    private BookCollectionMapper bookCollectionMapper;

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<List<BookCollectionDTO>> findAll(@ParameterObject Pageable pageable) {
        return wrapPageToList(
                this.bookCollectionService.findAllPaginated(pageable)
                        .map(bookCollectionMapper::toDTO)
        );
    }

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<BookCollectionDTO> findByUuid(@PathVariable String uuid) {
        return wrap(
                this.bookCollectionMapper.toDTO(
                        this.bookCollectionService.findByUuid(
                                UUID.fromString(uuid)
                        )
                )
        );
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<BookCollectionDTO> create(
            @RequestBody @Valid @JsonView(View.External.POST.class) BookCollectionDTO bookCollectionDTO) {
        return wrap(
                this.bookCollectionMapper.toDTO(
                        this.bookCollectionService.create(
                                this.bookCollectionMapper.toEntity(bookCollectionDTO)
                        )
                )
        );
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PutMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<BookCollectionDTO> update(
            @PathVariable("uuid") String uuid,
            @RequestBody @Valid @JsonView(View.External.PUT.class) BookCollectionDTO bookCollectionDTO) {
        return wrap(
                this.bookCollectionMapper.toDTO(
                        this.bookCollectionService.update(
                                UUID.fromString(uuid),
                                this.bookCollectionMapper.toEntity(bookCollectionDTO)
                        )
                )
        );
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping(path = "/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("uuid") String uuid) {
        this.bookCollectionService.deleteByUuid(UUID.fromString(uuid));
    }


}
