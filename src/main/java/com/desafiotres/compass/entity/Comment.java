package com.desafiotres.compass.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Comment {

    @Id
    private Long id;

    @Column(name = "body")
    private String body;

    @Column(name = "post_id")
    Long postId;

}
