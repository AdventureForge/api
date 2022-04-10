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

@Schema(name = "Publisher")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PublisherDTO {

    private Integer id;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class})
    private UUID uuid;

    @NotNull
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String name;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String websiteUrl;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String description;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String logo;

    @JsonView(value = {View.External.GET.class})
    private Set<UUID> booksUuids;
}
