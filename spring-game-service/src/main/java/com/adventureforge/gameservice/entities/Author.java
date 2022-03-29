package com.adventureforge.gameservice.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString()
@AttributeOverride(name = "id", column = @Column(name = "author_id"))
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Author extends BaseEntity {

    @Column
    private String firstName;

    @Column
    private String lastname;

    @Column
    private String pseudo;
}
