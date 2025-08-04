package com.example.liquibase_demo.service;

import com.example.liquibase_demo.dto.GroupPostDTO;
import com.example.liquibase_demo.entity.Group;
import com.example.liquibase_demo.entity.GroupPost;
import com.example.liquibase_demo.entity.Post;
import com.example.liquibase_demo.entity.User;
import com.example.liquibase_demo.repository.GroupPostRepository;
import com.example.liquibase_demo.repository.GroupRepository;
import com.example.liquibase_demo.repository.PostRepository;
import com.example.liquibase_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
//import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupPostService {

    @Autowired
    private GroupPostRepository groupPostRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    public GroupPostDTO create(GroupPostDTO dto) {
        Group group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        GroupPost gp = new GroupPost(group, user, post, dto.getContent(), LocalDateTime.now(), null);
        GroupPost saved = groupPostRepository.save(gp);

        return toDTO(saved);
    }

    public GroupPostDTO getById(Integer id) {
        GroupPost gp = groupPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupPost not found"));
        return toDTO(gp);
    }

    public List<GroupPostDTO> getAll() {
        return groupPostRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public GroupPostDTO update(Integer id, GroupPostDTO dto) {
        GroupPost existing = groupPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupPost not found"));

        existing.setContent(dto.getContent());
        existing.setUpdatedAt(LocalDateTime.now());

        GroupPost updated = groupPostRepository.save(existing);
        return toDTO(updated);
    }

    public boolean delete(Integer id) {
        if (!groupPostRepository.existsById(id)) return false;
        groupPostRepository.deleteById(id);
        return true;
    }

    private GroupPostDTO toDTO(GroupPost entity) {
        return new GroupPostDTO(
                entity.getId(),
                entity.getGroup().getId(),
                entity.getUser().getId(),
                entity.getPost().getId(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}