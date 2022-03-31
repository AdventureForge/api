package com.adventureforge.gameservice.dtos;


import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class AuthorDTO {

    private UUID uuid;
    private String firstName;
    private String lastname;
    private String pseudo;
    private Set<UUID> booksUuids;
}
