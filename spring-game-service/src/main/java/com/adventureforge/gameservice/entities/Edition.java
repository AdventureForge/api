package com.adventureforge.gameservice.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @JoinColumn(name = "roleplayinggame_id")
    @JsonIgnoreProperties(value = "editions")
    private RolePlayingGame rolePlayingGame;

    @OneToMany(mappedBy = "edition")
    @JsonIgnoreProperties(value = {"books", "edition"})
    @ToString.Exclude
    private Set<BookCollection> bookCollections;
}