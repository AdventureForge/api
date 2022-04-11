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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Locale;
import java.util.Set;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "books")
@AttributeOverride(name = "id", column = @Column(name = "book_id"))
public class Book extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(length = 512)
    private String subtitle;

    @Column
    private String cover;

    @Column(length = 65535)
    private String description;

    @Column(length = 10)
    private Locale language;

    @Column
    private String isbn;

    @Enumerated(EnumType.STRING)
    private BookCategory bookCategory;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    @JsonIgnoreProperties(value = "books")
    private BookCollection bookCollection;

    @ManyToMany
    @JoinTable(
            name = "author_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @JsonIgnoreProperties(value = "adventures")
    @ToString.Exclude
    private Set<Author> authors;
}
