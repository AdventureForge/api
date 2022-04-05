package com.adventureforge.adventureservice.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Table(name = "npc")
@AttributeOverride(name = "id", column = @Column(name = "npc_id"))
public class Npc extends BaseEntity {

    @Column(name = "name")
    private String name;
}
