package com.adventureforge.gameservice.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "illustrators")
//@AttributeOverride(name = "id", column = @Column(name = "illustrator_id"))
@PrimaryKeyJoinColumn(name = "illustrator_id")
public class Illustrator extends Author {

    @ManyToMany(mappedBy = "illustrators")
    @ToString.Exclude
    private List<Book> books;
}
