package com.adventureforge.gameservice.entities;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @Column(length = 65535)
    private String description;

    @Column
    private String pictureUrl;

    @Column(length = 2048)
    private String websiteUrl;

    @OneToMany(mappedBy = "rolePlayingGame")
    @JsonIgnoreProperties(value = "rolePlayingGame")
    @ToString.Exclude
    private List<Edition> editions;
}
