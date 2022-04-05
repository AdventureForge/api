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
@Table(name = "campaign")
@AttributeOverride(name = "id", column = @Column(name = "campaign_id"))
public class Campaign extends BaseEntity {

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(
            name = "campaign_adventure",
            joinColumns = @JoinColumn(name = "campaign_id"),
            inverseJoinColumns = @JoinColumn(name = "adventure_id")
    )
    @JsonIgnoreProperties(value = "campaigns")
    @ToString.Exclude
    private Set<Adventure> adventures;
}
