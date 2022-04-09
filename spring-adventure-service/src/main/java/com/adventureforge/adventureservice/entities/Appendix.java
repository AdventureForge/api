package com.adventureforge.adventureservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "appendix")
@AttributeOverride(name = "id", column = @Column(name = "appendix_id"))
public class Appendix extends BaseEntity {

    @Column
    private String title;

    @Column(length = 1024)
    private String description;

    @Lob
    private String textContent;

    @ManyToMany(mappedBy = "appendices")
    @ToString.Exclude
    private Set<Adventure> adventures;
}
