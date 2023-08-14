package com.desafiotres.compass.services;

import com.desafiotres.compass.client.PostClient;
import com.desafiotres.compass.dtos.PostDTO;
import com.desafiotres.compass.entity.Comment;
import com.desafiotres.compass.entity.History;
import com.desafiotres.compass.entity.Post;
import com.desafiotres.compass.enums.PostStatus;
import com.desafiotres.compass.exception.PostNotFoundException;
import com.desafiotres.compass.repository.CommentRepository;
import com.desafiotres.compass.repository.HistoryRepository;
import com.desafiotres.compass.repository.PostRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ApiService {

    private final PostClient postClient;
    private final PostRepository postRepository;
    private final HistoryRepository historyRepository;
    private final CommentRepository commentRepository;
    private final Comment commment;


    public void createPost(Long postId) {
        PostDTO postDTO = postClient.getPostById(postId);
        if (postDTO != null) {
            Post post = new Post();
            post.setId(postDTO.getId());
            postRepository.save(post);

            History history = new History();
            history.setDate(new Date());
            history.setStatus(PostStatus.CREATED.toString());
            history.setPost(post);
            historyRepository.save(history);

            processPost(postId);
        } else {
            failed(postId);
        }
    }

    public void processPost(Long postId) {
        try {
            PostDTO postDTO = postClient.getPostById(postId);
            if (postDTO != null) {
                Post post = new Post();
                post.setId(postDTO.getId());
                post.setTitle(postDTO.getTitle());
                post.setBody(postDTO.getBody());
                post.setState(PostStatus.POST_FIND);
                postRepository.save(post);

                History history = new History();
                history.setDate(new Date());
                history.setStatus(PostStatus.POST_FIND.toString());
                history.setPost(post);
                historyRepository.save(history);

                postOk(postId);
            } else {
                failed(postId);
            }
        } catch (FeignException.NotFound feignNotFound) {
            throw new PostNotFoundException("Post not found for ID: " + postId);
        }
    }

    public void postOk(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(""));
        post.setState(PostStatus.POST_OK);
    }

    public void commentFind(Long commentId) {

        commentOk(commentId);
    }

    public void commentOk(Long commentId) {

        enabled(commentId);
    }

    public void disablePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found"));
        if ((post != null && post.getState() == PostStatus.ENABLED) || (post != null && post.getState() == PostStatus.FAILED)) {
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
        try {
            Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(""));
            if (post != null && (post.getState() == PostStatus.ENABLED || post.getState() == PostStatus.DISABLED)) {
                post.setState(PostStatus.UPDATING);
                postRepository.save(post);

                History history = new History();
                history.setDate(new Date());
                history.setStatus(PostStatus.UPDATING.toString());
                history.setPost(post);
                historyRepository.save(history);

                processPost(postId);
            } else {
                failed(postId);
            }

        } catch (FeignException.NotFound feignNotFound) {
            throw new PostNotFoundException("Post not found for ID: " + postId);
        }
    }

    public void enabled(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(""));
        post.setState(PostStatus.ENABLED);

        History history = new History();
        history.setDate(new Date());
        history.setStatus(PostStatus.ENABLED.toString());
        historyRepository.save(history);
    }

    public void failed(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(""));
        post.setState(PostStatus.FAILED);

        History history = new History();
        history.setDate(new Date());
        history.setStatus(PostStatus.FAILED.toString());
        historyRepository.save(history);

        disablePost(postId);
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