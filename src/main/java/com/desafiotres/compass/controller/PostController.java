package com.desafiotres.compass.controller;

import com.desafiotres.compass.dtos.PostDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


public interface PostController {

    @PostMapping("/{postId}")
    ResponseEntity<String> processPost(@PathVariable Long postId);

    @DeleteMapping("/{postId}")
    ResponseEntity<String> disablePost(@PathVariable Long postId);

    @PutMapping("/{postId}")
    ResponseEntity<String> reprocessPost(@PathVariable Long postId);

    @GetMapping("/posts")
    ResponseEntity<List<PostDTO>> getPosts();
}