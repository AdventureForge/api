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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "scene")
@AttributeOverride(name = "id", column = @Column(name = "scene_id"))
public class Scene extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(length = 1024)
    private String description;

    @Lob
    private String textContent;

    @ManyToOne
    @JoinColumn(name = "adventure_id", nullable = false)
    @JsonIgnoreProperties(value = "scenes")
    @ToString.Exclude
    private Adventure adventure;

    @ManyToMany
    @JoinTable(
            name = "scene_scene",
            joinColumns = @JoinColumn(name = "scene_a_id"),
            inverseJoinColumns = @JoinColumn(name = "scene_b_id")
    )
    @JsonIgnoreProperties(value = {"previousScenes", "nextScenes"})
    @ToString.Exclude
    private Set<Scene> nextScenes;

    @ManyToMany(mappedBy = "nextScenes")
    @JsonIgnoreProperties(value = {"previousScenes", "nextScenes"})
    @ToString.Exclude
    private Set<Scene> previousScenes;

    @ManyToMany(mappedBy = "scenes")
    @ToString.Exclude
    private Set<Npc> npcs;

    @ManyToMany(mappedBy = "scenes")
    @ToString.Exclude
    private Set<Place> places;
}
