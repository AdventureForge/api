package com.adventureforge.gameservice.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Schema(name = "Collection")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookCollectionDTO {

    @JsonView(value = {View.External.GET.class, View.External.PUT.class})
    private UUID uuid;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    @NotNull
    private String title;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String description;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private UUID editionUuid;

    @JsonView(value = {View.External.GET.class})
    private Set<UUID> booksUuids;
}
