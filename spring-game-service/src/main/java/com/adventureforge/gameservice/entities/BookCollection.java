package com.adventureforge.gameservice.entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookCollection {

    private int id;
    private UUID uuid;
    private String title;
    private String description;
    private Edition edition;

    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    private String userCreated;
    private String userModified;
}
