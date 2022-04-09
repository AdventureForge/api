package com.adventureforge.adventureservice.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Schema(name = "Npc")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PlaceDTO {

    @JsonView(value = {View.External.GET.class, View.External.PUT.class})
    private UUID uuid;

    @NotNull
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String name;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String description;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String textContent;

    @Size(max = 2048)
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String pictureUrl;

    @JsonView(value = {View.External.GET.class})
    private Set<AdventureDTO> adventures;

    @JsonView(value = {View.External.GET.class})
    private Set<SceneDTO> scenes;
}
