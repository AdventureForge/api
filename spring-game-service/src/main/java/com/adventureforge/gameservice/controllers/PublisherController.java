package com.adventureforge.gameservice.controllers;

import com.adventureforge.gameservice.controllers.wrappers.ResponseWrapper;
import com.adventureforge.gameservice.dto.PublisherDTO;
import com.adventureforge.gameservice.dto.View;
import com.adventureforge.gameservice.mappers.PublisherMapper;
import com.adventureforge.gameservice.services.PublisherService;
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

@Tag(name = "Publishers")
@AllArgsConstructor
@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private PublisherService publisherService;
    private PublisherMapper publisherMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<List<PublisherDTO>> findAllPaginated(@ParameterObject Pageable pageable) {

        return ResponseWrapper.wrapPageToList(
                this.publisherService.findAllPaginated(pageable)
                        .map(publisherMapper::toDTO)
        );
    }

    @GetMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<PublisherDTO> findByUuid(@PathVariable("uuid") String uuid) {
        return wrap(
                publisherMapper.toDTO(
                        this.publisherService.findByUuid(UUID.fromString(uuid))
                )
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<PublisherDTO> create(
            @RequestBody @JsonView(View.External.POST.class) @Valid PublisherDTO publisherDTO) {
        return wrap(
                this.publisherMapper.toDTO(
                        this.publisherService.save(
                                this.publisherMapper.toEntity(publisherDTO)
                        )
                )
        );
    }

    @PutMapping(path = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<PublisherDTO> update(
            @PathVariable("uuid") String uuid,
            @RequestBody @JsonView(View.External.PUT.class) @Valid PublisherDTO publisherDTO) {
        return wrap(
                this.publisherMapper.toDTO(
                        this.publisherService.update(
                                UUID.fromString(uuid),
                                this.publisherMapper.toEntity(publisherDTO)
                        )
                )
        );
    }

    @DeleteMapping(path = "/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("uuid") String uuid) {
        this.publisherService.deleteByUuid(UUID.fromString(uuid));
    }
}
