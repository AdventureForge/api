package com.adventureforge.gameservice.entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Publisher {
    private int id;
    private UUID uuid;
    private String name;
    private String websiteUrl;
    private String description;
    private String logo;

    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    private String userCreated;
    private String userModified;

    private List<Book> books;
}
