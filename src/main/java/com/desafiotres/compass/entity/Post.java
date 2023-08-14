package com.desafiotres.compass.entity;

import com.desafiotres.compass.converter.PostStatusConverter;
import com.desafiotres.compass.enums.PostStatus;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;



import lombok.Data;

@Data
@Entity
public class Post {
    @Id
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Convert(converter = PostStatusConverter.class)
    @Column(name = "state")
    private PostStatus state;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @OrderBy("id Asc") private Set<Comment> comments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @OrderBy("id Asc") private Set<History> histories = new HashSet<>();
}

