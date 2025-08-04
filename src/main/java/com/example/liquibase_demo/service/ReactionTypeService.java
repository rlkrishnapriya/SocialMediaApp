package com.example.liquibase_demo.service;

import com.example.liquibase_demo.dto.ReactionTypeDTO;
import com.example.liquibase_demo.entity.ReactionType;
import com.example.liquibase_demo.repository.ReactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReactionTypeService {

    @Autowired
    private ReactionTypeRepository reactionTypeRepository;

    private ReactionTypeDTO convertToDTO(ReactionType reactionType) {
        return new ReactionTypeDTO(
                reactionType.getId(),
                reactionType.getType(),
                reactionType.getCreatedAt(),
                reactionType.getUpdatedAt(),
                reactionType.getDeletedAt()
        );
    }

    private ReactionType convertToEntity(ReactionTypeDTO dto) {
        ReactionType reactionType = new ReactionType();
        reactionType.setId(dto.getId());
        reactionType.setType(dto.getType());
        reactionType.setCreatedAt(dto.getCreatedAt());
        reactionType.setUpdatedAt(dto.getUpdatedAt());
        reactionType.setDeletedAt(dto.getDeletedAt());
        return reactionType;
    }

    public ReactionTypeDTO createReactionType(ReactionTypeDTO dto) {
        ReactionType reactionType = convertToEntity(dto);
        reactionType.setCreatedAt(LocalDateTime.now());
        ReactionType saved = reactionTypeRepository.save(reactionType);
        return convertToDTO(saved);
    }

    public List<ReactionTypeDTO> getAllReactionTypes() {
        return reactionTypeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ReactionTypeDTO getReactionTypeById(Integer id) {
        ReactionType reactionType = reactionTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ReactionType not found"));
        return convertToDTO(reactionType);
    }

    public ReactionTypeDTO updateReactionType(Integer id, ReactionTypeDTO dto) {
        ReactionType existing = reactionTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ReactionType not found"));

        existing.setType(dto.getType());
        existing.setUpdatedAt(LocalDateTime.now());
        ReactionType updated = reactionTypeRepository.save(existing);
        return convertToDTO(updated);
    }

    public void deleteReactionType(Integer id) {
        if (!reactionTypeRepository.existsById(id)) {
            throw new RuntimeException("ReactionType not found");
        }
        reactionTypeRepository.deleteById(id);
    }
}