package com.example.liquibase_demo.service;

import com.example.liquibase_demo.dto.ReactionDTO;
import com.example.liquibase_demo.entity.Reaction;
import com.example.liquibase_demo.entity.ReactionType;
import com.example.liquibase_demo.entity.User;
import com.example.liquibase_demo.repository.ReactionRepository;
import com.example.liquibase_demo.repository.ReactionTypeRepository;
import com.example.liquibase_demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReactionService {

    private final ReactionRepository reactionRepository;
    private final UserRepository userRepository;
    private final ReactionTypeRepository reactionTypeRepository;

    public ReactionService(ReactionRepository reactionRepository,
                           UserRepository userRepository,
                           ReactionTypeRepository reactionTypeRepository) {
        this.reactionRepository = reactionRepository;
        this.userRepository = userRepository;
        this.reactionTypeRepository = reactionTypeRepository;
    }

    public List<ReactionDTO> getAllReactions() {
        return reactionRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ReactionDTO getReactionById(int id) {
        Reaction reaction = reactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reaction not found with id " + id));
        return convertToDTO(reaction);
    }

    public ReactionDTO createReaction(ReactionDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        ReactionType reactionType = reactionTypeRepository.findById(dto.getReactionTypeId())
                .orElseThrow(() -> new EntityNotFoundException("ReactionType not found"));

        Reaction reaction = new Reaction();
        reaction.setUser(user);
        reaction.setParentId(dto.getPostId());
        reaction.setParentType(dto.getParentType());
        reaction.setReactionType(reactionType);
        reaction.setCreatedAt(dto.getCreatedAt());

        Reaction saved = reactionRepository.save(reaction);
        return convertToDTO(saved);
    }

    public ReactionDTO updateReaction(int id, ReactionDTO dto) {
        Reaction reaction = reactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reaction not found"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        ReactionType reactionType = reactionTypeRepository.findById(dto.getReactionTypeId())
                .orElseThrow(() -> new EntityNotFoundException("ReactionType not found"));

        reaction.setUser(user);
        reaction.setParentId(dto.getPostId());
        reaction.setParentType(dto.getParentType());
        reaction.setReactionType(reactionType);
        reaction.setCreatedAt(dto.getCreatedAt());

        Reaction updated = reactionRepository.save(reaction);
        return convertToDTO(updated);
    }

    public void deleteReaction(int id) {
        reactionRepository.deleteById(id);
    }

    private ReactionDTO convertToDTO(Reaction reaction) {
        ReactionDTO dto = new ReactionDTO();
        dto.setId(reaction.getId());
        dto.setUserId(reaction.getUser().getId());
        dto.setPostId(reaction.getParentId());
        dto.setParentType(reaction.getParentType());
        dto.setReactionTypeId(reaction.getReactionType().getId());
        dto.setCreatedAt(reaction.getCreatedAt());
        return dto;
    }
}