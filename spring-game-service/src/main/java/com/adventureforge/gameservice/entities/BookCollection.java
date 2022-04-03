package com.adventureforge.gameservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JsonIgnoreProperties(value = "bookCollections")
    private Edition edition;

    @OneToMany(mappedBy = "bookCollection")
    @JsonIgnoreProperties(value = "bookCollection")
    @ToString.Exclude
    private List<Book> books;
}
