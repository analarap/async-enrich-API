package com.desafiotres.compass.client;

import com.desafiotres.compass.dtos.PostDTO;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "PostClient", url = "https://jsonplaceholder.typicode.com")
public interface PostClient {

    @GetMapping("/posts/{postId}")
    PostDTO getPostById(@PathVariable Long postId);

}
