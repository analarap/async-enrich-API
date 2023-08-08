package com.desafiotres.compass.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("title")
        private String title;

        @JsonProperty("body")
        private String body;

}

