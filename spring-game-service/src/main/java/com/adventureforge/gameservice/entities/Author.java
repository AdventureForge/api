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
import javax.persistence.ManyToMany;
import java.util.Set;

@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "author_id"))
public class Author extends BaseEntity {

    @Column(length = 255)
    private String firstname;

    @Column(length = 255)
    private String lastname;

    @ManyToMany(mappedBy = "authors")
    @JsonIgnoreProperties(value = "authors")
    @ToString.Exclude
    private Set<Book> books;
}
