package com.example.liquibase_demo.service;

import com.example.liquibase_demo.entity.ReactionType;
import com.example.liquibase_demo.repository.ReactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReactionTypeService {

    @Autowired
    private ReactionTypeRepository reactionTypeRepository;

    public ReactionType createReactionType(String type) {
        if (reactionTypeRepository.existsByType(type)) {
            throw new RuntimeException("Reaction type already exists: " + type);
        }

        ReactionType reactionType = new ReactionType();
        reactionType.setType(type);
        reactionType.setCreatedAt(LocalDateTime.now());

        return reactionTypeRepository.save(reactionType);
    }

    public List<ReactionType> getAllReactionTypes() {
        return reactionTypeRepository.findAll();
    }

    public Optional<ReactionType> getReactionTypeById(Integer id) {
        return reactionTypeRepository.findById(id);
    }
}