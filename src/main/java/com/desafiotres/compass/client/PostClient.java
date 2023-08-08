package com.desafiotres.compass.client;

import com.desafiotres.compass.dtos.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "PostClient", url = "https://jsonplaceholder.typicode.com")
public interface PostClient {

    @GetMapping("/posts/{postId}")
    PostDTO getPostById(@PathVariable Long postId);

}
