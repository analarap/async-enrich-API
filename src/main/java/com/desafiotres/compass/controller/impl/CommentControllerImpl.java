package com.desafiotres.compass.controller.impl;

import com.desafiotres.compass.dtos.CommentDTO;
import com.desafiotres.compass.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/posts")
@RestController
@RequiredArgsConstructor
public class CommentControllerImpl {

    private final CommentService commentService;

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsForPost(@PathVariable Long postId) {
        List<CommentDTO> comments = commentService.getCommentsForPost(postId);
        return ResponseEntity.ok(comments);
    }
}
