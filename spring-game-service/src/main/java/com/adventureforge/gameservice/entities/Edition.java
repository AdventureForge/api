package com.adventureforge.gameservice.entities;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
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
    private RolePlayingGame rolePlayingGame;

    @OneToMany(mappedBy = "edition")
    @ToString.Exclude
    private List<BookCollection> bookCollections;
}