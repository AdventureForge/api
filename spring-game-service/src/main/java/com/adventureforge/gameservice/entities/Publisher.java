package com.adventureforge.gameservice.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Builder
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
    @ToString.Exclude
    private List<Book> books;
}
