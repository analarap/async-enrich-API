package com.desafiotres.compass.controller.impl;

import com.desafiotres.compass.dtos.PostDTO;
import com.desafiotres.compass.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@RestController
@RequiredArgsConstructor
public class PostControllerImpl {

    private final PostService postService;
    private final JmsTemplate jmsTemplate;

    @PostMapping("/{postId}")
    public ResponseEntity<String> processPost(@PathVariable Long postId) {
        postService.processPost(postId);
        jmsTemplate.convertAndSend("queue.post.processed", postId);
        return ResponseEntity.ok("Post processing initiated.");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> disablePost(@PathVariable Long postId) {
        postService.disablePost(postId);
        jmsTemplate.convertAndSend("queue.post.disabled", postId);
        return ResponseEntity.ok("Post disabled successfully.");
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> reprocessPost(@PathVariable Long postId) {
        postService.reprocessPost(postId);
        jmsTemplate.convertAndSend("queue.post.reprocessed", postId);
        return ResponseEntity.ok("Post reprocessing initiated.");
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getPosts() {
        List<PostDTO> posts = postService.getAllPosts();+
        return ResponseEntity.ok(posts);
    }
}




