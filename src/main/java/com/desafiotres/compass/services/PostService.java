package com.desafiotres.compass.services;

import com.desafiotres.compass.client.CommentClient;
import com.desafiotres.compass.client.PostClient;
import com.desafiotres.compass.dtos.CommentDTO;
import com.desafiotres.compass.dtos.PostDTO;
import com.desafiotres.compass.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {


    private final PostClient postClient;



    // posts
    public void processPost(Long postId) {
        System.out.println(postClient.getPostById(postId));
    }

    public void disablePost(Long postId) {

    }

    public void reprocessPost(Long postId) {

    }

    public List<PostDTO> getAllPosts() {
        return null;
    }


    // comments


}