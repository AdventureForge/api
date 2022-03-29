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
@Table(name = "books")
@AttributeOverride(name = "id", column = @Column(name = "book_id"))
public class Book extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column
    private String subtitle;

    @Column
    private String cover;

    @Column
    private String description;

    @Column
    private String language;

    @Column
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "publisher_id", nullable = false)
    @ToString.Exclude
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private BookCollection bookCollection;

    @Enumerated(EnumType.STRING)
    private BookCategory bookCategory;

    @ManyToMany
    @JoinTable(
            name = "writers_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "writer_id")
    )
    @ToString.Exclude
    private List<Writer> writers;

    @ManyToMany
    @JoinTable(
            name = "illustrators_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "illustrator_id")
    )
    @ToString.Exclude
    private List<Illustrator> illustrators;


}
