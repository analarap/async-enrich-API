package com.desafiotres.compass.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

        private List<CommentDTO> comments;

        public List<CommentDTO> getComments() {
                return comments;
        }

        public void setComments(List<CommentDTO> comments) {
                this.comments = comments;
        }

        @JsonProperty("id")
        private Long id;

        @JsonProperty("title")
        private String title;

        @JsonProperty("body")
        private String body;

}

