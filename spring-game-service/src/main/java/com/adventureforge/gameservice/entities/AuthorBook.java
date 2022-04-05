package com.adventureforge.gameservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AuthorBook implements Serializable {

    @EmbeddedId
    private AuthorBookKey authorBookKey;

    @ManyToOne
    @MapsId("bookId")
    @JsonIgnoreProperties("authorBooks")
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @MapsId("authorId")
    @JsonIgnoreProperties(value = "authorBooks")
    @JoinColumn(name = "author_id")
    private Author author;

    @Column
    private boolean writer;

    @Column
    private boolean illustrator;

    @Column(unique = true, nullable = false)
    private UUID uuid;

    @CreatedDate
    private LocalDateTime dateCreated;

    @LastModifiedDate
    private LocalDateTime lastModified;

    @CreatedBy
    private String userCreated;

    @LastModifiedBy
    private String userModified;
}
