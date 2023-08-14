package com.desafiotres.compass.services;

import com.desafiotres.compass.client.CommentClient;
import com.desafiotres.compass.client.PostClient;
import com.desafiotres.compass.dtos.CommentDTO;
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
    private final CommentClient commentClient;


    public void createPost(Long postId) {
        PostDTO postDTO = postClient.getPostById(postId);
        if (postDTO != null) {
            Post post = new Post();
            post.setId(postDTO.getId());
            post.setState(PostStatus.CREATED);
            postRepository.save(post);

            History history = new History();
            history.setDate(new Date());
            history.setStatus(PostStatus.CREATED.toString());
            history.setPostId(postId);
            historyRepository.save(history);

            processPost(postId);
        } else {
            failed(postId);
        }
    }

    public void processPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(""));
        try {
            PostDTO postDTO = postClient.getPostById(postId);
            if (postDTO != null) {
                Post postbd = new Post();
                postbd.setId(postDTO.getId());
                postbd.setTitle(postDTO.getTitle());
                postbd.setBody(postDTO.getBody());
                postbd.setHistories(post.getHistories());
                postbd.setComments(post.getComments());
                postbd.setState(PostStatus.POST_FIND);
                postRepository.save(postbd);

                History history = new History();
                history.setDate(new Date());
                history.setStatus(PostStatus.POST_FIND.toString());
                history.setPostId(postId);
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
        if (post != null) {
            post.setState(PostStatus.POST_OK);
            postRepository.save(post);

            History history = new History();
            history.setDate(new Date());
            history.setStatus(PostStatus.POST_OK.toString());
            history.setPostId(postId);
            historyRepository.save(history);

        } else {
            disablePost(postId);
        }
    }

    public void commentFind(Long postId) {
        List<CommentDTO> listComments = commentClient.getCommentsByPostId(postId);
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(""));
        if(listComments != null){
            post.setState(PostStatus.COMMENTS_OK);
            postRepository.save(post);

            for(CommentDTO commentDTO : listComments) {
                Comment comment = new Comment();
                comment.setPostId(postId);
                comment.setId(commentDTO.getId());
                comment.setBody(commentDTO.getBody());
                commentRepository.save(comment);
            }

            History history = new History();
            history.setDate(new Date());
            history.setStatus(PostStatus.COMMENTS_FIND.toString());
            history.setPostId(postId);
            historyRepository.save(history);

            commentOk(postId);

        } else {
            disablePost(postId);
        }
    }

    public void commentOk(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(""));
        if (post != null) {
            post.setState(PostStatus.COMMENTS_OK);
            postRepository.save(post);

            History history = new History();
            history.setDate(new Date());
            history.setStatus(PostStatus.COMMENTS_OK.toString());
            history.setPostId(postId);
            historyRepository.save(history);

            enabled(postId);
        } else {
            //excecao post n encontrado
        }
    }

    public void disablePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found"));
        if ((post != null && post.getState() == PostStatus.ENABLED) || (post != null && post.getState() == PostStatus.FAILED)) {
            post.setState(PostStatus.DISABLED);
            postRepository.save(post);

            History history = new History();
            history.setDate(new Date());
            history.setStatus(PostStatus.DISABLED.toString());
            history.setPostId(postId);
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
                history.setPostId(postId);
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
        postRepository.save(post);

        History history = new History();
        history.setDate(new Date());
        history.setStatus(PostStatus.ENABLED.toString());
        history.setPostId(postId);
        historyRepository.save(history);
    }

    public void failed(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(""));
        post.setState(PostStatus.FAILED);

        History history = new History();
        history.setDate(new Date());
        history.setStatus(PostStatus.FAILED.toString());
        history.setPostId(postId);
        historyRepository.save(history);

        disablePost(postId);
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream().map(PostDTO::new).toList();
    }
}