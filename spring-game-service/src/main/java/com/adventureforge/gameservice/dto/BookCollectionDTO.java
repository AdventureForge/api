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
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Schema(name = "Collection")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookCollectionDTO {

    @JsonView
    private Integer id;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class})
    private UUID uuid;

    @NotEmpty
    @Size(min = 1, max = 255, message = "Collection title must not be empty")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String title;

    @Size(max = 65535)
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String description;

    @NotNull
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private UUID editionUuid;

    @NotNull
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private UUID publisherUuid;

    @JsonView(value = {View.External.GET.class})
    private UUID rolePlayingGameUuid;

    @JsonView(value = {View.External.GET.class})
    private Set<UUID> booksUuids;

    @JsonView(value = {View.External.GET.class})
    private String dateCreated;

    @JsonView(value = {View.External.GET.class})
    private String lastModified;

    @JsonView(value = {View.External.GET.class})
    private String userCreated;

    @JsonView(value = {View.External.GET.class})
    private String userModified;
}
