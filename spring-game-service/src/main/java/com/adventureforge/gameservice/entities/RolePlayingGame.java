package com.adventureforge.gameservice.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "roleplayinggames")
@AttributeOverride(name = "id", column = @Column(name = "roleplayinggame_id"))
public class RolePlayingGame extends BaseEntity {

    @Column
    private String title;

    @Column
    private String subtitle;

    @Column
    private String pictureUrl;

    @Column
    private String websiteUrl;

    @OneToMany(mappedBy = "rolePlayingGame")
    @ToString.Exclude
    private List<Edition> editions;
}
