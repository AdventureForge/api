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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "publishers")
@AttributeOverride(name = "id", column = @Column(name = "publisher_id"))
public class Publisher extends BaseEntity {

    @Column
    private String name;

    @Column(length = 2048)
    private String websiteUrl;

    @Column(length = 65535)
    private String description;

    @Column
    private String logo;

    @OneToMany(mappedBy = "publisher")
    @JsonIgnoreProperties(value = "publisher")
    @ToString.Exclude
    private Set<BookCollection> bookCollections;
}
