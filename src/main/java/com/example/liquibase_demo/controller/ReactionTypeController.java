package com.example.liquibase_demo.controller;

import com.example.liquibase_demo.entity.ReactionType;
import com.example.liquibase_demo.service.ReactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reaction-types")
public class ReactionTypeController {

    @Autowired
    private ReactionTypeService reactionTypeService;

    @PostMapping
    public ResponseEntity<ReactionType> createReactionType(@RequestParam String type) {
        ReactionType reactionType = reactionTypeService.createReactionType(type);
        return ResponseEntity.ok(reactionType);
    }

    @GetMapping
    public ResponseEntity<List<ReactionType>> getAllReactionTypes() {
        return ResponseEntity.ok(reactionTypeService.getAllReactionTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReactionType> getReactionTypeById(@PathVariable Integer id) {
        return reactionTypeService.getReactionTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}