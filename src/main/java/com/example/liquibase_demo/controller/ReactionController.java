package com.example.liquibase_demo.controller;

import com.example.liquibase_demo.dto.ReactionDTO;
import com.example.liquibase_demo.service.ReactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reactions")
public class ReactionController {

    private final ReactionService reactionService;

    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @GetMapping
    public List<ReactionDTO> getAllReactions() {
        return reactionService.getAllReactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReactionDTO> getReactionById(@PathVariable int id) {
        return ResponseEntity.ok(reactionService.getReactionById(id));
    }

    @PostMapping
    public ResponseEntity<ReactionDTO> createReaction(@RequestBody ReactionDTO dto) {
        return ResponseEntity.ok(reactionService.createReaction(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReactionDTO> updateReaction(@PathVariable int id, @RequestBody ReactionDTO dto) {
        return ResponseEntity.ok(reactionService.updateReaction(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReaction(@PathVariable int id) {
        reactionService.deleteReaction(id);
        return ResponseEntity.noContent().build();
    }
}