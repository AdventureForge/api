package com.adventureforge.gameservice.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "editions")
@AttributeOverride(name = "id", column = @Column(name = "edition_id"))
public class Edition extends BaseEntity {

    @Column
    private int editionNumber;

    @Column
    private String editionTitle;

    @ManyToOne
    @JoinColumn(name = "roleplayinggame_id", nullable = false)
    @JsonIgnoreProperties(value = "editions")
    private RolePlayingGame rolePlayingGame;

    @OneToMany(mappedBy = "edition")
    @JsonIgnoreProperties(value = {"books", "edition"})
    @ToString.Exclude
    private Set<BookCollection> bookCollections;
}