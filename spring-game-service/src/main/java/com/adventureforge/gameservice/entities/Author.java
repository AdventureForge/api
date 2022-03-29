package com.adventureforge.gameservice.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "authors")
@AttributeOverride(name = "id", column = @Column(name = "author_id"))
public abstract class Author extends BaseEntity {

    @Column
    private String firstName;

    @Column
    private String lastname;

    @Column
    private String pseudo;

    @ManyToMany(mappedBy = "authors")
    @ToString.Exclude
    private List<RolePlayingGame> rolePlayingGames;

    @ManyToMany(mappedBy = "authors")
    @ToString.Exclude
    private List<Book> books;
}
