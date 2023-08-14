package com.desafiotres.compass.dtos;

import com.desafiotres.compass.entity.Comment;
import com.desafiotres.compass.entity.History;
import com.desafiotres.compass.entity.Post;
import com.desafiotres.compass.enums.PostStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

        private Set<Comment> comments;

        private Set<History> histories = new HashSet<>();


        @JsonProperty("id")
        private Long id;

        @JsonProperty("title")
        private String title;

        @JsonProperty("body")
        private String body;

        private PostStatus state;

        public PostDTO(Post post) {
                this.id = post.getId();
                this.title = post.getTitle();
                this.body = post.getBody();
                this.state = post.getState();
                this.comments = post.getComments();
                this.histories = post.getHistories();
        }
}

