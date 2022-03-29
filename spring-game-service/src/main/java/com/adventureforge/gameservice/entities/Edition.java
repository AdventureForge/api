package com.adventureforge.gameservice.entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Edition {

    private int id;
    private UUID uuid;
    private RolePlayingGame rolePlayingGame;
    private int editionNumber;
    private String editionTitle;

    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    private String userCreated;
    private String userModified;
}