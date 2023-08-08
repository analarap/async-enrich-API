package com.desafiotres.compass.client;


import com.desafiotres.compass.dtos.CommentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "CommentClient", url = "https://jsonplaceholder.typicode.com")
public interface CommentClient {

    @GetMapping("/posts/{postId}/comments")
    List<CommentDTO> getCommentsByPostId(@PathVariable Long postId);
}
