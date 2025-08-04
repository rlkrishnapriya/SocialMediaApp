package com.example.liquibase_demo.controller;

import com.example.liquibase_demo.dto.ReactionTypeDTO;
import com.example.liquibase_demo.service.ReactionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reaction-types")
public class ReactionTypeController {

    @Autowired
    private ReactionTypeService reactionTypeService;

    @PostMapping
    public ReactionTypeDTO createReactionType(@RequestBody ReactionTypeDTO dto) {
        return reactionTypeService.createReactionType(dto);
    }

    @GetMapping
    public List<ReactionTypeDTO> getAllReactionTypes() {
        return reactionTypeService.getAllReactionTypes();
    }

    @GetMapping("/{id}")
    public ReactionTypeDTO getReactionTypeById(@PathVariable Integer id) {
        return reactionTypeService.getReactionTypeById(id);
    }

    @PutMapping("/{id}")
    public ReactionTypeDTO updateReactionType(@PathVariable Integer id, @RequestBody ReactionTypeDTO dto) {
        return reactionTypeService.updateReactionType(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteReactionType(@PathVariable Integer id) {
        reactionTypeService.deleteReactionType(id);
    }
}