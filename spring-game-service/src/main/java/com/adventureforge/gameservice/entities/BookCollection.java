package com.adventureforge.gameservice.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "collections")
@AttributeOverride(name = "id", column = @Column(name = "collection_id"))
public class BookCollection extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Edition edition;

    @OneToMany(mappedBy = "bookCollection")
    @ToString.Exclude
    private List<Book> books;
}
