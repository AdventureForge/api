package com.adventureforge.gameservice.controllers;

import com.adventureforge.gameservice.controllers.wrappers.ResponseWrapper;
import com.adventureforge.gameservice.dto.BookDTO;
import com.adventureforge.gameservice.dto.View;
import com.adventureforge.gameservice.mappers.BookMapper;
import com.adventureforge.gameservice.services.BookService;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.adventureforge.gameservice.controllers.wrappers.ResponseWrapper.wrap;
import static com.adventureforge.gameservice.controllers.wrappers.ResponseWrapper.wrapPageToList;

@Tag(name = "Books")
@AllArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private BookMapper bookMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<List<BookDTO>> findAllPaginated(@ParameterObject Pageable pageable) {
        return wrapPageToList(
                this.bookService.findAll(pageable)
                        .map(bookMapper::toDTO)
        );
    }

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<BookDTO> create(
            @RequestBody @JsonView(View.External.POST.class) @Valid BookDTO bookDTO) {
        return wrap(
                this.bookMapper.toDTO(
                        this.bookService.create(
                                this.bookMapper.toEntity(bookDTO)
                        )
                )
        );
    }

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

    @DeleteMapping(path = "/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("uuid") String uuid) {
        this.bookService.delete(UUID.fromString(uuid));
    }
}