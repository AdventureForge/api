package com.adventureforge.gameservice.entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RolePlayingGame {

    private int id;
    private UUID uuid;
    private String title;
    private String subtitle;
    private String pictureUrl;
    private String websiteUrl;

    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    private String userCreated;
    private String userModified;
}
