package com.example.liquibase_demo.service;

import com.example.liquibase_demo.dto.GroupMemberDTO;
import com.example.liquibase_demo.entity.Group;
import com.example.liquibase_demo.entity.GroupMember;
import com.example.liquibase_demo.entity.GroupRole;
import com.example.liquibase_demo.entity.User;
import com.example.liquibase_demo.repository.GroupMemberRepository;
import com.example.liquibase_demo.repository.GroupRepository;
import com.example.liquibase_demo.repository.GroupRoleRepository;
import com.example.liquibase_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupMemberService {

    @Autowired private GroupMemberRepository groupMemberRepository;
    @Autowired private GroupRepository groupRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private GroupRoleRepository groupRoleRepository;

    public GroupMemberDTO createGroupMember(GroupMemberDTO dto) {
        Group group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        GroupRole role = groupRoleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        GroupMember member = new GroupMember();
        member.setGroup(group);
        member.setUser(user);
        member.setRole(role);
        member.setCreatedAt(LocalDateTime.now());

        return toDTO(groupMemberRepository.save(member));
    }

    public GroupMemberDTO getGroupMemberById(Integer id) {
        GroupMember member = groupMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupMember not found"));
        return toDTO(member);
    }

    public List<GroupMemberDTO> getAllGroupMembers() {
        return groupMemberRepository.findAll()
                .stream().map(this::toDTO)
                .collect(Collectors.toList());
    }

    public GroupMemberDTO updateGroupMember(Integer id, GroupMemberDTO dto) {
        GroupMember existing = groupMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("GroupMember not found"));

        if (dto.getGroupId() != null) {
            Group group = groupRepository.findById(dto.getGroupId())
                    .orElseThrow(() -> new RuntimeException("Group not found"));
            existing.setGroup(group);
        }

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            existing.setUser(user);
        }

        if (dto.getRoleId() != null) {
            GroupRole role = groupRoleRepository.findById(dto.getRoleId())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            existing.setRole(role);
        }

        existing.setUpdatedAt(LocalDateTime.now());

        return toDTO(groupMemberRepository.save(existing));
    }

    public boolean deleteGroupMember(Integer id) {
        Optional<GroupMember> optional = groupMemberRepository.findById(id);
        if (optional.isPresent()) {
            groupMemberRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private GroupMemberDTO toDTO(GroupMember member) {
        return new GroupMemberDTO(
                member.getId(),
                member.getGroup().getId(),
                member.getUser().getId(),
                member.getRole().getId(),
                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }
}