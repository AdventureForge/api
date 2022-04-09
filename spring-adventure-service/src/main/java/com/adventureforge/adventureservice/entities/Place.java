package com.adventureforge.adventureservice.entities;

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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "place")
@AttributeOverride(name = "id", column = @Column(name = "place_id"))
public class Place extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(length = 1024)
    private String description;

    @Lob
    private String textContent;

    @Column(length = 2048)
    private String pictureUrl;

    @ManyToMany
    @JoinTable(
            name = "place_adventure",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "adventure_id")
    )
    @JsonIgnoreProperties(value = "places")
    @ToString.Exclude
    private Set<Adventure> adventures;

    @ManyToMany
    @JoinTable(
            name = "place_scene",
            joinColumns = @JoinColumn(name = "place_id"),
            inverseJoinColumns = @JoinColumn(name = "scene_id")
    )
    @JsonIgnoreProperties(value = "places")
    @ToString.Exclude
    private Set<Scene> scenes;
}
