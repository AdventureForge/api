package com.adventureforge.gameservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthorDTO {

    @JsonView(value = View.GET.class)
    private UUID uuid;

    @NotNull
    @JsonView(value = {View.GET.class, View.PUT.class, View.POST.class})
    private String firstname;

    @JsonView(value = {View.GET.class, View.PUT.class, View.POST.class})
    private String lastname;

    //@JsonView(value = {View.NONE.class})
    private Set<UUID> booksUuids;
}
