package com.desafiotres.compass.controller.impl;

import com.desafiotres.compass.dtos.PostDTO;
import com.desafiotres.compass.services.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@RestController
@RequiredArgsConstructor
public class PostControllerImpl {

    private final ApiService apiService;
    private final JmsTemplate jmsTemplate;

    @PostMapping("/{postId}")
    public ResponseEntity<String> processPost(@PathVariable Long postId) {
        apiService.createPost(postId);
        jmsTemplate.convertAndSend("queue.post.processed", postId);
        return ResponseEntity.ok("Post processing initiated.");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> disablePost(@PathVariable Long postId) {
        apiService.disablePost(postId);
        jmsTemplate.convertAndSend("queue.post.disabled", postId);
        return ResponseEntity.ok("Post disabled successfully.");
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> reprocessPost(@PathVariable Long postId) {
        apiService.reprocessPost(postId);
        jmsTemplate.convertAndSend("queue.post.reprocessed", postId);
        return ResponseEntity.ok("Post reprocessing initiated.");
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getPosts() {
        List<PostDTO> posts = apiService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
}




