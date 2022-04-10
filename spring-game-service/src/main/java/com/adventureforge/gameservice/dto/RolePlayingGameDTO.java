package com.adventureforge.gameservice.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Schema(name = "RolePlayingGame")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RolePlayingGameDTO {

    private Integer id;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class})
    private UUID uuid;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    @NotNull
    private String title;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String subtitle;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String pictureUrl;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String websiteUrl;

    @JsonView(value = {View.External.GET.class})
    private Set<UUID> editionsUuids;
}
