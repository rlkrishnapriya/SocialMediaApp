package com.example.liquibase_demo.service;

import com.example.liquibase_demo.entity.Reaction;
import com.example.liquibase_demo.entity.ReactionType;
import com.example.liquibase_demo.repository.ReactionRepository;
import com.example.liquibase_demo.repository.ReactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    @Autowired
    private ReactionTypeRepository reactionTypeRepository;

    public Reaction saveReaction(Reaction reaction) {
        return reactionRepository.save(reaction);
    }

    public List<Reaction> getReactionsByUserId(Integer userId) {
        return reactionRepository.findByUserId(userId);
    }

    public List<Reaction> getReactionsByParent(Integer parentId, String parentType) {
        return reactionRepository.findByParentIdAndParentType(parentId, parentType);
    }

    public Optional<Reaction> getReactionByUserAndParent(Integer userId, Integer parentId, String parentType) {
        return reactionRepository.findByUserIdAndParentIdAndParentType(userId, parentId, parentType);
    }

    public long countReactionsByType(Integer parentId, String parentType, String reactionTypeName) {
        Optional<ReactionType> type = reactionTypeRepository.findByType(reactionTypeName);
        return type.map(rt -> reactionRepository.countByParentIdAndParentTypeAndReactionType(parentId, parentType, rt))
                   .orElse(0L);
    }

    public void deleteReaction(Integer id) {
        reactionRepository.deleteById(id);
    }
}