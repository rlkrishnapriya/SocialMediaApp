package com.example.liquibase_demo.service;

import com.example.liquibase_demo.entity.PostMedia;
import com.example.liquibase_demo.repository.PostMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostMediaService {

    private final PostMediaRepository postMediaRepository;

    @Autowired
    public PostMediaService(PostMediaRepository postMediaRepository) {
        this.postMediaRepository = postMediaRepository;
    }

    public PostMedia createPostMedia(PostMedia media) {
        media.setCreatedAt(LocalDateTime.now());
        return postMediaRepository.save(media);
    }

    public Optional<PostMedia> getPostMediaById(Integer id) {
        return postMediaRepository.findById(id);
    }

    public List<PostMedia> getMediaByPostId(Integer postId) {
        return postMediaRepository.findByPostId(postId);
    }

    public List<PostMedia> getAllPostMedia() {
        return postMediaRepository.findAll();
    }

    public PostMedia updatePostMedia(Integer id, PostMedia updated) {
        PostMedia existing = postMediaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found with id " + id));

        existing.setMediaUrl(updated.getMediaUrl());
        return postMediaRepository.save(existing);
    }

    public void deletePostMedia(Integer id) {
        if (!postMediaRepository.existsById(id)) {
            throw new RuntimeException("Media not found with id " + id);
        }
        postMediaRepository.deleteById(id);
    }
}