package com.adventureforge.gameservice.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
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

    @ManyToMany
    @JoinTable(
            name = "writers_roleplayinggames",
            joinColumns = @JoinColumn(name = "roleplayinggame_id"),
            inverseJoinColumns = @JoinColumn(name = "writer_id")
    )
    @ToString.Exclude
    private List<Writer> writers;
}
