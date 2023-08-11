package com.desafiotres.compass.services;

import com.desafiotres.compass.client.PostClient;
import com.desafiotres.compass.dtos.PostDTO;
import com.desafiotres.compass.entity.History;
import com.desafiotres.compass.entity.Post;
import com.desafiotres.compass.enums.PostStatus;
import com.desafiotres.compass.repository.HistoryRepository;
import com.desafiotres.compass.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostClient postClient;
    private final PostRepository postRepository;
    private final HistoryRepository historyRepository;

    public void processPost(Long postId) {
        PostDTO postDTO = postClient.getPostById(postId);
        if (postDTO != null) {
            Post post = new Post();
            post.setId(postDTO.getId());
            post.setTitle(postDTO.getTitle());
            post.setBody(postDTO.getBody());
            post.setState(PostStatus.POST_OK);
            postRepository.save(post);

            History history = new History();
            history.setDate(new Date());
            history.setStatus(PostStatus.POST_OK.toString());
            history.setPost(post);
            historyRepository.save(history);
        }
    }

    public void disablePost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null && post.getState() == PostStatus.ENABLED) {
            post.setState(PostStatus.DISABLED);
            postRepository.save(post);

            History history = new History();
            history.setDate(new Date());
            history.setStatus(PostStatus.DISABLED.toString());
            history.setPost(post);
            historyRepository.save(history);
        }
    }

    public void reprocessPost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null && (post.getState() == PostStatus.ENABLED || post.getState() == PostStatus.DISABLED)) {
            post.setState(PostStatus.UPDATING);
            postRepository.save(post);

            History history = new History();
            history.setDate(new Date());
            history.setStatus(PostStatus.UPDATING.toString());
            history.setPost(post);
            historyRepository.save(history);

            processPost(postId);
        }
    }

    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOs = new ArrayList<>();
        for (Post post : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(post.getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setBody(post.getBody());
            postDTOs.add(postDTO);
        }
        return postDTOs;
    }
}