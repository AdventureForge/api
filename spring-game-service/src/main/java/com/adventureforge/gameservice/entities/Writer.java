package com.adventureforge.gameservice.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "writers")
//@AttributeOverride(name = "id", column = @Column(name = "writer_id"))
@PrimaryKeyJoinColumn(name = "writer_id")
public class Writer extends Author {

    @ManyToMany(mappedBy = "writers")
    @ToString.Exclude
    private List<Book> books;

    @ManyToMany(mappedBy = "writers")
    @ToString.Exclude
    private List<RolePlayingGame> rolePlayingGames;
}
