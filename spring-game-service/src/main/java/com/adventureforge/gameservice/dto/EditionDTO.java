package com.adventureforge.gameservice.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Schema(name = "Edition")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EditionDTO {

    @JsonView
    private Integer id;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class})
    private UUID uuid;

    @NotEmpty
    @Positive(message = "Edition number must be greater than zero")
    @Size(max = 1, message = "Edition number max value is 100")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private int editionNumber;

    @NotEmpty(message = "Edition title must not be empty, size between 1 and 255")
    @Size(min = 1, max = 255, message = "title must be between 1 and 255")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String editionTitle;

    @NotNull
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private UUID rolePlayingGameUuid;

    @JsonView(value = {View.External.GET.class})
    private Set<UUID> collectionsUuids;
}
