package com.adventureforge.gameservice.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class AuthorBookKey implements Serializable {

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "author_id")
    private Integer authorId;
}
