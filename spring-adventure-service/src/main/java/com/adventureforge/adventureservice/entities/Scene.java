package com.adventureforge.adventureservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
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
}
