package com.adventureforge.gameservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Publisher extends BaseEntity {

    @Column
    private String name;

    @Column
    private String websiteUrl;

    @Column
    private String description;

    @Column
    private String logo;

    @OneToMany(mappedBy = "publisher")
    @JsonIgnoreProperties(value = "publisher")
    @ToString.Exclude
    private Set<Book> books;
}
