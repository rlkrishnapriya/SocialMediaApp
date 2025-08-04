package com.example.liquibase_demo.service;

import com.example.liquibase_demo.dto.GroupRoleDTO;
import com.example.liquibase_demo.entity.GroupRole;
import com.example.liquibase_demo.repository.GroupRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupRoleService {

    @Autowired
    private GroupRoleRepository groupRoleRepository;

    public GroupRoleDTO create(GroupRoleDTO dto) {
        if (groupRoleRepository.existsByRole(dto.getRole())) {
            throw new RuntimeException("Role already exists");
        }

        GroupRole role = new GroupRole();
        role.setRole(dto.getRole());
        role.setDescription(dto.getDescription());
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(null);

        GroupRole saved = groupRoleRepository.save(role);
        return toDTO(saved);
    }

    public GroupRoleDTO getById(Integer id) {
        GroupRole role = groupRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group role not found"));
        return toDTO(role);
    }

    public List<GroupRoleDTO> getAll() {
        return groupRoleRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public GroupRoleDTO update(Integer id, GroupRoleDTO dto) {
        GroupRole existing = groupRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group role not found"));

        existing.setRole(dto.getRole());
        existing.setDescription(dto.getDescription());
        existing.setUpdatedAt(LocalDateTime.now());

        GroupRole updated = groupRoleRepository.save(existing);
        return toDTO(updated);
    }

    public boolean delete(Integer id) {
        if (!groupRoleRepository.existsById(id)) return false;
        groupRoleRepository.deleteById(id);
        return true;
    }

    private GroupRoleDTO toDTO(GroupRole role) {
        return new GroupRoleDTO(role.getId(), role.getRole(), role.getDescription());
    }
}