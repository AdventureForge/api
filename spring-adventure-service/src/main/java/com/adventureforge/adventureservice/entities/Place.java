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
@Table(name = "place")
@AttributeOverride(name = "id", column = @Column(name = "place_id"))
public class Place extends BaseEntity {

    @Column(name = "name")
    private String name;
}
