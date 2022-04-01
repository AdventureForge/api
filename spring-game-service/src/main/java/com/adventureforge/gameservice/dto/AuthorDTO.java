package com.adventureforge.gameservice.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class AuthorDTO {

    private UUID uuid;

    @NotNull
    private String firstname;
    private String lastname;
    private Set<UUID> booksUuids;
}
