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
@Table(name = "collections")
@AttributeOverride(name = "id", column = @Column(name = "collection_id"))
public class BookCollection extends BaseEntity {

    @Column
    private String title;

    @Column(length = 65535)
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties(value = "bookCollections")
    private Edition edition;

    @OneToMany(mappedBy = "bookCollection")
    @JsonIgnoreProperties(value = "bookCollection")
    @ToString.Exclude
    private Set<Book> books;

    // Outside data model
    public static final String DEFAULT_COLLECTION = "default";
}
