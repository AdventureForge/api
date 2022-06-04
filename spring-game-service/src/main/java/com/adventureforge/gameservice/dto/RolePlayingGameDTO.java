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

@Schema(name = "RolePlayingGame")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RolePlayingGameDTO {

    @JsonView
    private Integer id;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class})
    private UUID uuid;

    @NotEmpty(message = "RolePlayingGame title must not be empty")
    @Size(min = 1, max = 255, message = "title length must be between 1 and 255")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String title;

    @Size(max = 255, message = "subtitle length must be between 1 and 255")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String subtitle;

    @Size(max = 65535, message = "description length must be between 1 and 65535")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String description;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String pictureUrl;

    @URL
    @Size(max = 2048, message = "url max length is 2048 characters")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String websiteUrl;

    @JsonView(value = {View.External.GET.class})
    private Set<UUID> editionsUuids;

    @JsonView(value = {View.External.GET.class})
    private String dateCreated;

    @JsonView(value = {View.External.GET.class})
    private String lastModified;

    @JsonView(value = {View.External.GET.class})
    private String userCreated;

    @JsonView(value = {View.External.GET.class})
    private String userModified;
}
