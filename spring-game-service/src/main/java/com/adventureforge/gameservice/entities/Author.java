package com.adventureforge.gameservice.entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public abstract class Author {

    private int id;
    private UUID uuid;
    private String firstName;
    private String lastname;
    private String pseudo;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    private String userCreated;
    private String userModified;

    private List<RolePlayingGame> rolePlayingGames;
    private List<Book> books;
}
