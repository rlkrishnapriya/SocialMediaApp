package com.example.liquibase_demo.controller;

import com.example.liquibase_demo.entity.PostMedia;
import com.example.liquibase_demo.service.PostMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post-media")
public class PostMediaController {

    private final PostMediaService postMediaService;

    @Autowired
    public PostMediaController(PostMediaService postMediaService) {
        this.postMediaService = postMediaService;
    }

    @PostMapping
    public ResponseEntity<PostMedia> createPostMedia(@RequestBody PostMedia media) {
        PostMedia saved = postMediaService.createPostMedia(media);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostMedia> getPostMediaById(@PathVariable Integer id) {
        return postMediaService.getPostMediaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<PostMedia>> getMediaByPostId(@PathVariable Integer postId) {
        List<PostMedia> mediaList = postMediaService.getMediaByPostId(postId);
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping
    public ResponseEntity<List<PostMedia>> getAllPostMedia() {
        return ResponseEntity.ok(postMediaService.getAllPostMedia());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostMedia> updatePostMedia(@PathVariable Integer id, @RequestBody PostMedia media) {
        PostMedia updated = postMediaService.updatePostMedia(id, media);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostMedia(@PathVariable Integer id) {
        postMediaService.deletePostMedia(id);
        return ResponseEntity.noContent().build();
    }
}