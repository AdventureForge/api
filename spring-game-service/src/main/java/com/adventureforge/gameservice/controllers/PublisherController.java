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

@Tag(name = "Publishers")
@AllArgsConstructor
@RestController
@RequestMapping("/publishers")
public class PublisherController {

    private PublisherService publisherService;
    private PublisherMapper publisherMapper;

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<List<PublisherDTO>> findAllPaginated(@ParameterObject Pageable pageable) {

        return ResponseWrapper.wrapPageToList(
                this.publisherService.findAllPaginated(pageable)
                        .map(publisherMapper::toDTO)
        );
    }

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
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

    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(value = View.External.GET.class)
    public ResponseWrapper<PublisherDTO> create(
            @RequestBody @JsonView(View.External.POST.class) @Valid PublisherDTO publisherDTO) {
        return wrap(
                this.publisherMapper.toDTO(
                        this.publisherService.create(
                                this.publisherMapper.toEntity(publisherDTO)
                        )
                )
        );
    }

    @RolesAllowed({"ROLE_ADMIN"})
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

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping(path = "/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("uuid") String uuid) {
        this.publisherService.deleteByUuid(UUID.fromString(uuid));
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteList(@RequestBody List<String> uuids) {
        List<UUID> uuidList = uuids
                .stream()
                .map(UUID::fromString)
                .toList();

        this.publisherService.deleteByListOfUuids(uuidList);
    }
}
