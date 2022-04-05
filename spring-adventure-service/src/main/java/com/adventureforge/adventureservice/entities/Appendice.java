package com.adventureforge.adventureservice.entities;

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
@Table(name = "appendice")
@AttributeOverride(name = "id", column = @Column(name = "appendice_id"))
public class Appendice extends BaseEntity {

    @Column
    private String title;

    @ManyToMany(mappedBy = "appendices")
    @ToString.Exclude
    private Set<Adventure> adventures;
}
