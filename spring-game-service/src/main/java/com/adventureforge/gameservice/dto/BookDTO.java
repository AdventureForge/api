package com.adventureforge.gameservice.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Schema(name = "Book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookDTO {

    @JsonView(value = {View.External.GET.class, View.External.PUT.class})
    private UUID bookUuid;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String title;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String cover;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String description;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String language;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String isbn;

    @JsonView(value = {View.External.GET.class})
    private Set<UUID> authorsUuid;

    @JsonView(value = {View.External.GET.class})
    private UUID publisherUuid;

    @JsonView(value = {View.External.GET.class})
    private UUID collectionUuid;

    @JsonView(value = {View.External.GET.class})
    private UUID rolePlayingGameUuid;

    @JsonView(value = {View.External.GET.class})
    private UUID editionUuid;

    @JsonView(value = {View.External.GET.class})
    private String category;
}
