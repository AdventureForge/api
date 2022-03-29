package com.adventureforge.gameservice.entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class Book {

    private int id;
    private UUID uuid;
    private String title;
    private String subtitle;
    private String cover;
    private String description;
    private String language;
    private String isbn;
    private Publisher publisher;
    private BookCollection bookCollection;
    private BookCategory bookCategory;

    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    private String userCreated;
    private String userModified;

    private List<Author> authors;
}
