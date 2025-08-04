package com.example.liquibase_demo.controller;

import com.example.liquibase_demo.dto.GroupMemberDTO;
import com.example.liquibase_demo.service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group-members")
public class GroupMemberController {

    @Autowired private GroupMemberService groupMemberService;

    @PostMapping
    public ResponseEntity<GroupMemberDTO> create(@RequestBody GroupMemberDTO dto) {
        return ResponseEntity.ok(groupMemberService.createGroupMember(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupMemberDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(groupMemberService.getGroupMemberById(id));
    }

    @GetMapping
    public ResponseEntity<List<GroupMemberDTO>> getAll() {
        return ResponseEntity.ok(groupMemberService.getAllGroupMembers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupMemberDTO> update(@PathVariable Integer id, @RequestBody GroupMemberDTO dto) {
        return ResponseEntity.ok(groupMemberService.updateGroupMember(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        boolean deleted = groupMemberService.deleteGroupMember(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}