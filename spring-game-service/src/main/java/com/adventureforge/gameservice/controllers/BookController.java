package com.adventureforge.gameservice.controllers;

import com.adventureforge.gameservice.controllers.wrappers.ResponseWrapper;
import com.adventureforge.gameservice.dto.BookDTO;
import com.adventureforge.gameservice.dto.View;
import com.adventureforge.gameservice.mappers.BookMapper;
import com.adventureforge.gameservice.services.BookService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
@Tag(name = "Books")
@AllArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private BookMapper bookMapper;

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<List<BookDTO>> findAllPaginated(@ParameterObject Pageable pageable) {
        return wrapPageToList(
                this.bookService.findAll(pageable)
                        .map(bookMapper::toDTO)
        );
    }

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(path = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<BookDTO> findByUuid(@PathVariable("uuid") String uuid) {
        return wrap(
                this.bookMapper.toDTO(
                        this.bookService.findByUuid(
                                UUID.fromString(uuid)
                        )
                )
        );
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<BookDTO> create(
            @RequestBody @JsonView(View.External.POST.class) @Valid BookDTO bookDTO) {
        log.info(bookDTO.toString());
        return wrap(
                this.bookMapper.toDTO(
                        this.bookService.createBookWithDependencies(
                                this.bookMapper.toEntity(bookDTO)
                        )
                )
        );
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PutMapping(path = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<BookDTO> update(
            @PathVariable("uuid") String uuid,
            @RequestBody @Valid @JsonView(View.External.PUT.class) BookDTO bookDTO) {
        return wrap(
                this.bookMapper.toDTO(
                        this.bookService.update(
                                UUID.fromString(uuid),
                                this.bookMapper.toEntity(bookDTO)
                        )
                )
        );
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping(path = "/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("uuid") String uuid) {
        this.bookService.deleteByUuid(UUID.fromString(uuid));
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteList(@RequestBody List<String> uuids) {
        List<UUID> uuidList = uuids
                .stream()
                .map(UUID::fromString)
                .toList();

        this.bookService.deleteByListOfUuids(uuidList);
    }
}
