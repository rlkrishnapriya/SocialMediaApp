package com.example.liquibase_demo.controller;

import com.example.liquibase_demo.dto.GroupPostDTO;
import com.example.liquibase_demo.service.GroupPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group-posts")
public class GroupPostController {

    @Autowired
    private GroupPostService groupPostService;

    @PostMapping
    public ResponseEntity<GroupPostDTO> create(@RequestBody GroupPostDTO dto) {
        return ResponseEntity.ok(groupPostService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupPostDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(groupPostService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<GroupPostDTO>> getAll() {
        return ResponseEntity.ok(groupPostService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupPostDTO> update(@PathVariable Integer id, @RequestBody GroupPostDTO dto) {
        return ResponseEntity.ok(groupPostService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = groupPostService.delete(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}