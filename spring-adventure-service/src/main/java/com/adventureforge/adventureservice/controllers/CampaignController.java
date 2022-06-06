package com.adventureforge.adventureservice.controllers;

import com.adventureforge.adventureservice.controllers.wrappers.ResponseWrapper;
import com.adventureforge.adventureservice.dto.CampaignDTO;
import com.adventureforge.adventureservice.dto.View;
import com.adventureforge.adventureservice.mappers.CampaignMapper;
import com.adventureforge.adventureservice.services.CampaignService;
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
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import static com.adventureforge.adventureservice.controllers.wrappers.ResponseWrapper.wrap;
import static com.adventureforge.adventureservice.controllers.wrappers.ResponseWrapper.wrapPageToList;

@Slf4j
@Tag(name = "Campaign")
@AllArgsConstructor
@RestController
@RequestMapping("/campaigns")
public class CampaignController {

    private final CampaignService campaignService;
    private final CampaignMapper campaignMapper;

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<List<CampaignDTO>> findAllPaginated(@ParameterObject Pageable pageable, Principal principal) {
        return wrapPageToList(
                this.campaignService
                        .findAllPaginatedFilteredByUserCreated(principal.getName(), pageable)
                        .map(campaignMapper::toDTO)
        );
    }

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping(path = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<CampaignDTO> findByUuid(@PathVariable("uuid") String uuid, Principal principal) {
        return wrap(
                this.campaignMapper.toDTO(
                        this.campaignService.findByUuidAndUsername(
                                UUID.fromString(uuid),
                                principal.getName()
                        )
                )
        );
    }

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<CampaignDTO> create(
            @RequestBody @JsonView(View.External.POST.class) @Valid CampaignDTO campaignDTO) {
        return wrap(
                this.campaignMapper.toDTO(
                        this.campaignService.createWithDependencies(
                                this.campaignMapper.toEntity(campaignDTO)
                        )
                )
        );
    }

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @PutMapping(path = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    @JsonView(View.External.GET.class)
    public ResponseWrapper<CampaignDTO> update(
            @PathVariable("uuid") String uuid,
            @RequestBody @Valid @JsonView(View.External.PUT.class) CampaignDTO campaignDTO, Principal principal) {
        return wrap(
                this.campaignMapper.toDTO(
                        this.campaignService.update(
                                UUID.fromString(uuid),
                                this.campaignMapper.toEntity(campaignDTO),
                                principal.getName()
                        )
                )
        );
    }

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping(path = "/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("uuid") String uuid, Principal principal) {
        this.campaignService.deleteByUuid(UUID.fromString(uuid), principal.getName());
    }

    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteList(@RequestBody List<String> uuids, Principal principal) {
        List<UUID> uuidList = uuids
                .stream()
                .map(UUID::fromString)
                .toList();

        this.campaignService.deleteByListOfUuids(uuidList, principal.getName());
    }
}
