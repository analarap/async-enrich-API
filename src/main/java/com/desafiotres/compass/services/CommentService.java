package com.desafiotres.compass.services;

import com.desafiotres.compass.client.CommentClient;
import com.desafiotres.compass.dtos.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentClient commentClient;

    public List<CommentDTO> getCommentsForPost(Long postId) {
        return commentClient.getCommentsByPostId(postId);
    }
}
