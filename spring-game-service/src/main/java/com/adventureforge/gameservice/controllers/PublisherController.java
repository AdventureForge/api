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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseWrapper<Page<PublisherDTO>> findAllPaginated(@ParameterObject Pageable pageable) {
        return wrap(this.publisherService.findAllPaginated(pageable)
                .map(publisherMapper::toDTO));
    }
}
