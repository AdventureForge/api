package com.adventureforge.gameservice.dto;

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

@Schema(name = "Author")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthorDTO {

    private Integer id;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class})
    private UUID uuid;

    @NotNull
    @Size(min = 1, max = 255)
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String firstname;

    @Size(min = 1, max = 255)
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String lastname;

    @JsonView(value = {View.External.GET.class})
    private Set<UUID> booksUuids;
}
