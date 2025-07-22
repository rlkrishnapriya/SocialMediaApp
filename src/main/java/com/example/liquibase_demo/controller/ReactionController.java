package com.example.liquibase_demo.controller;

import com.example.liquibase_demo.entity.Reaction;
import com.example.liquibase_demo.service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reactions")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @PostMapping
    public Reaction createReaction(@RequestBody Reaction reaction) {
        return reactionService.saveReaction(reaction);
    }

    @GetMapping("/user/{userId}")
    public List<Reaction> getReactionsByUser(@PathVariable Integer userId) {
        return reactionService.getReactionsByUserId(userId);
    }

    @GetMapping("/parent")
    public List<Reaction> getReactionsByParent(
            @RequestParam Integer parentId,
            @RequestParam String parentType) {
        return reactionService.getReactionsByParent(parentId, parentType);
    }

    @GetMapping("/check")
    public Optional<Reaction> getReactionByUserAndParent(
            @RequestParam Integer userId,
            @RequestParam Integer parentId,
            @RequestParam String parentType) {
        return reactionService.getReactionByUserAndParent(userId, parentId, parentType);
    }

    @GetMapping("/count")
    public long countReactionsByType(
            @RequestParam Integer parentId,
            @RequestParam String parentType,
            @RequestParam String reactionType) {
        return reactionService.countReactionsByType(parentId, parentType, reactionType);
    }

    @DeleteMapping("/{id}")
    public void deleteReaction(@PathVariable Integer id) {
        reactionService.deleteReaction(id);
    }
}