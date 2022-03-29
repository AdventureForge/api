package com.adventureforge.gameservice.entities;

import lombok.*;

import javax.persistence.Entity;

@Builder
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Illustrator extends Author {
}
