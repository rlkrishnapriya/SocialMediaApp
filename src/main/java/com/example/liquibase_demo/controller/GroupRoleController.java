package com.example.liquibase_demo.controller;

import com.example.liquibase_demo.dto.GroupRoleDTO;
import com.example.liquibase_demo.service.GroupRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group-roles")
public class GroupRoleController {

    @Autowired
    private GroupRoleService groupRoleService;

    @PostMapping
    public ResponseEntity<GroupRoleDTO> create(@RequestBody GroupRoleDTO dto) {
        return ResponseEntity.ok(groupRoleService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupRoleDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(groupRoleService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<GroupRoleDTO>> getAll() {
        return ResponseEntity.ok(groupRoleService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupRoleDTO> update(@PathVariable Integer id, @RequestBody GroupRoleDTO dto) {
        return ResponseEntity.ok(groupRoleService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = groupRoleService.delete(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}