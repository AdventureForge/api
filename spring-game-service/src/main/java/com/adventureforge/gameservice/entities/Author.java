package com.adventureforge.gameservice.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString()
@AttributeOverride(name = "id", column = @Column(name = "author_id"))
public class Author extends BaseEntity {

    @Column
    private String firstName;

    @Column
    private String lastname;

    @Column
    private String pseudo;

    @OneToMany(mappedBy = "author")
    @ToString.Exclude
    private Set<AuthorBook> authorBooks;
}
