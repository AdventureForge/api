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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "adventure")
@ToString(callSuper = true)
@AttributeOverride(name = "id", column = @Column(name = "adventure_id"))
public class Adventure extends BaseEntity {

    @Column(name = "title")
    private String title;

    @ManyToMany(mappedBy = "adventures")
    @ToString.Exclude
    private Set<Campaign> campaigns;

    @OneToMany(mappedBy = "adventure")
    @JsonIgnoreProperties(value = "adventure")
    @ToString.Exclude
    private Set<Scene> scenes;

    @ManyToMany
    @JoinTable(
            name = "adventure_appendice",
            joinColumns = @JoinColumn(name = "adventure_id"),
            inverseJoinColumns = @JoinColumn(name = "appendice_id")
    )
    @JsonIgnoreProperties(value = "adventures")
    @ToString.Exclude
    private Set<Appendix> appendices;

    @ManyToMany
    @JoinTable(
            name = "scene_scene",
            joinColumns = @JoinColumn(name = "scene_a_id"),
            inverseJoinColumns = @JoinColumn(name = "scene_b_id")
    )
    @JsonIgnoreProperties(value = {"previousAdventures", "nextAdventures"})
    @ToString.Exclude
    private Set<Adventure> nextAdventures;

    @ManyToMany(mappedBy = "nextAdventures")
    @JsonIgnoreProperties(value = {"previousAdventures", "nextAdventures"})
    @ToString.Exclude
    private Set<Adventure> previousAdventures;

    @ManyToMany(mappedBy = "adventures")
    @ToString.Exclude
    private Set<Npc> npcs;

    @ManyToMany(mappedBy = "adventures")
    @ToString.Exclude
    private Set<Place> places;
}
