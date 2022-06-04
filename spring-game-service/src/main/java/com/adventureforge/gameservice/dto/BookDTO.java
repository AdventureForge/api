package com.adventureforge.gameservice.dto;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.ISBN;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Schema(name = "Book")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookDTO {

    @JsonView
    private Integer id;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class})
    private UUID uuid;

    @NotEmpty(message = "Book title must not be empty, size between 1 and 255")
    @Size(min = 1, max = 255)
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String title;

    @Size(max = 510, message = "maximum size = 510 characters")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String subtitle;

    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String cover;

    @Size(max = 65535, message = "maximum size = 65535 characters")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String description;

    @Size(max = 10, message = "locale must be at least 2 characters long")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String language;

    @NotEmpty(message = "a valid isbn is required")
    @ISBN
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String isbn;

    @NotNull(message = "authors associated to this book are required")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private Set<UUID> authorsUuid;

    @NotNull(message = "A book must be put in a collection")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private UUID collectionUuid;

    @NotEmpty(message = "A book category is required")
    @JsonView(value = {View.External.GET.class, View.External.PUT.class, View.External.POST.class})
    private String category;


    @JsonView(value = {View.External.GET.class})
    private UUID publisherUuid;

    @JsonView(value = {View.External.GET.class})
    private UUID rolePlayingGameUuid;

    @JsonView(value = {View.External.GET.class})
    private UUID editionUuid;

}
