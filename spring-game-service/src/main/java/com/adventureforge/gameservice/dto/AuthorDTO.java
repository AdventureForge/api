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
    @Schema()
    @JsonView
    private Integer id;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class})
    private UUID uuid;

    @NotNull
    @Size(min = 1, max = 255, message = "Firstname length must be between 1 and 255 characters")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String firstname;

    @Size(min = 1, max = 255, message = "Lastname length must be between 1 and 255 characters")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String lastname;

    @JsonView(value = {View.External.GET.class})
    private Set<UUID> booksUuids;
}
