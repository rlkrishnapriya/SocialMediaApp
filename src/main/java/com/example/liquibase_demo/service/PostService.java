package com.example.liquibase_demo.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.liquibase_demo.dto.PostDTO;
import com.example.liquibase_demo.dto.PostMediaDTO;
import com.example.liquibase_demo.entity.Post;
import com.example.liquibase_demo.entity.PostMedia;
import com.example.liquibase_demo.entity.User;
import com.example.liquibase_demo.repository.PostMediaRepository;
import com.example.liquibase_demo.repository.PostRepository;
import com.example.liquibase_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMediaRepository postMediaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cloudinary cloudinary;

    public PostDTO createPostWithMedia(PostDTO postDTO, List<MultipartFile> files) {
        User user = userRepository.findById(postDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setUser(user);
        post.setContent(postDTO.getContent());
        post.setCreatedAt(LocalDateTime.now());

        Post savedPost = postRepository.save(post);

        List<PostMediaDTO> mediaDTOList = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                try {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> uploadResult = (Map<String, Object>) cloudinary.uploader().upload(
                            file.getBytes(),
                            ObjectUtils.emptyMap()
                    );

                    String mediaUrl = uploadResult.get("secure_url").toString();

                    PostMedia media = new PostMedia();
                    media.setPost(savedPost);
                    media.setMediaUrl(mediaUrl);
                    media.setCreatedAt(LocalDateTime.now());

                    PostMedia savedMedia = postMediaRepository.save(media);

                    PostMediaDTO mediaDTO = new PostMediaDTO();
                    mediaDTO.setId(savedMedia.getId());
                    mediaDTO.setPostId(savedPost.getId());
                    mediaDTO.setMediaUrl(savedMedia.getMediaUrl());
                    mediaDTO.setCreatedAt(savedMedia.getCreatedAt());

                    mediaDTOList.add(mediaDTO);

                } catch (Exception e) {
                    throw new RuntimeException("Failed to upload media to Cloudinary", e);
                }
            }
        }

        PostDTO result = new PostDTO();
        result.setId(savedPost.getId());
        result.setUserId(user.getId());
        result.setContent(savedPost.getContent());
        result.setCreatedAt(savedPost.getCreatedAt());
        result.setMedia(mediaDTOList);

        return result;
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream().map(this::mapToPostDTO).collect(Collectors.toList());
    }

    public PostDTO getPostById(int id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        return mapToPostDTO(post);
    }

    public PostDTO updatePost(int id, PostDTO postDTO) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setContent(postDTO.getContent());
        Post updatedPost = postRepository.save(post);
        return mapToPostDTO(updatedPost);
    }

    public void deletePost(int id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found");
        }
        postRepository.deleteById(id);
    }

    private PostDTO mapToPostDTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setUserId(post.getUser().getId());
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt());

        List<PostMediaDTO> mediaDTOs = post.getMedia().stream().map(media -> {
            PostMediaDTO mediaDTO = new PostMediaDTO();
            mediaDTO.setId(media.getId());
            mediaDTO.setPostId(post.getId());
            mediaDTO.setMediaUrl(media.getMediaUrl());
            mediaDTO.setCreatedAt(media.getCreatedAt());
            return mediaDTO;
        }).collect(Collectors.toList());

        dto.setMedia(mediaDTOs);
        return dto;
    }
}