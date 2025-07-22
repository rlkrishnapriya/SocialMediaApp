package com.example.liquibase_demo.service;

import com.example.liquibase_demo.entity.Post;
import com.example.liquibase_demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    public Optional<Post> getPostById(Integer id) {
        return postRepository.findById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post updatePost(Integer id, Post updatedPost) {
        Post existing = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + id));

        existing.setContent(updatedPost.getContent());
        existing.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(existing);
    }

    public void deletePost(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + id));

        post.setDeletedAt(LocalDateTime.now());
        postRepository.save(post);
    }
}