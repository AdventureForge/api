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
import javax.persistence.Lob;
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
@Table(name = "books")
@AttributeOverride(name = "id", column = @Column(name = "book_id"))
public class Book extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column
    private String subtitle;

    @Column
    private String cover;

    @Lob
    private String description;

    @Column
    private String language;

    @Column
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    @JsonIgnoreProperties(value = "books")
    @ToString.Exclude
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    @JsonIgnoreProperties(value = "books")
    private BookCollection bookCollection;

    @Enumerated(EnumType.STRING)
    private BookCategory bookCategory;

    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties(value = "book")
    @ToString.Exclude
    private Set<AuthorBook> authorBooks;
}
