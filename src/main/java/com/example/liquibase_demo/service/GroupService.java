package com.example.liquibase_demo.service;

import com.example.liquibase_demo.dto.GroupDTO;
import com.example.liquibase_demo.entity.Group;
import com.example.liquibase_demo.entity.User;
import com.example.liquibase_demo.repository.GroupRepository;
import com.example.liquibase_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public GroupDTO createGroup(GroupDTO dto) {
        User creator = userRepository.findById(dto.getCreatedBy())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Group group = new Group();
        group.setCreatedBy(creator);
        group.setDisplayName(dto.getDisplayName());
        group.setDescription(dto.getDescription());
        group.setCreatedAt(LocalDateTime.now());
        group.setUpdatedAt(LocalDateTime.now());

        group = groupRepository.save(group);
        return toDTO(group);
    }

    public GroupDTO getGroupById(Integer id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return toDTO(group);
    }

    public List<GroupDTO> getAllGroups() {
        return groupRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public GroupDTO updateGroup(Integer id, GroupDTO dto) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        group.setDisplayName(dto.getDisplayName());
        group.setDescription(dto.getDescription());
        group.setUpdatedAt(LocalDateTime.now());

        return toDTO(groupRepository.save(group));
    }

    public void deleteGroup(Integer id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        groupRepository.delete(group);
    }

    private GroupDTO toDTO(Group group) {
        return new GroupDTO(
                group.getId(),
                group.getCreatedBy().getId(),
                group.getDisplayName(),
                group.getDescription(),
                null,
                group.getCreatedAt(),
                group.getUpdatedAt(),
                null
        );
    }
}