package com.adventureforge.adventureservice.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Schema(name = "Adventure")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AdventureDTO {

    @JsonView(value = {View.External.GET.class, View.External.PUT.class})
    private UUID uuid;

    @NotNull
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String title;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String description;

    @JsonView(value = {View.External.GET.class})
    private Set<CampaignDTO> campaigns;

    @JsonView(value = {View.External.GET.class})
    private Set<SceneDTO> scenes;

    @JsonView(value = {View.External.GET.class})
    private Set<AppendixDTO> appendices;

    @JsonView(value = {View.External.GET.class})
    private Set<AdventureDTO> nextAdventures;

    @JsonView(value = {View.External.GET.class})
    private Set<AdventureDTO> previousAdventures;

    @JsonView(value = {View.External.GET.class})
    private Set<NpcDTO> npcs;

    @JsonView(value = {View.External.GET.class})
    private Set<PlaceDTO> places;
}
