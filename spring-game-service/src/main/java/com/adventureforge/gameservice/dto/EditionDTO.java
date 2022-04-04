package com.adventureforge.gameservice.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Schema(name = "Edition")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EditionDTO {

    @JsonView(value = {View.External.GET.class, View.External.PUT.class})
    private UUID uuid;

    @NotNull
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private int editionNumber;

    @NotNull
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String editionTitle;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String rolePlayingGameUuid;

    @JsonView(value = {View.External.GET.class})
    private Set<UUID> collectionsUuids;
}
