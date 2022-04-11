package com.adventureforge.gameservice.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Schema(name = "Publisher")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PublisherDTO {

    @JsonView
    private Integer id;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class})
    private UUID uuid;

    @NotEmpty(message = "Publisher name must not be empty")
    @Size(min = 1, max = 255, message = "publisher name length must be between 1 and 255")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String name;

    @URL(message = "URL is invalid")
    @Size(min = 1, max = 2048, message = "url length must be between 1 and 2048")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String websiteUrl;

    @Size(min = 1, max = 65535, message = "url length must be between 1 and 65535")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String description;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String logo;

    @JsonView(value = {View.External.GET.class})
    private Set<UUID> booksUuids;
}
