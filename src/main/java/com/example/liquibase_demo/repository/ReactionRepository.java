package com.example.liquibase_demo.repository;

import com.example.liquibase_demo.entity.Reaction;
import com.example.liquibase_demo.entity.ReactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Integer> {

    
    List<Reaction> findByUserId(Integer userId);

    List<Reaction> findByParentIdAndParentType(Integer parentId, String parentType);

    Optional<Reaction> findByUserIdAndParentIdAndParentType(Integer userId, Integer parentId, String parentType);

    long countByParentIdAndParentTypeAndReactionType(Integer parentId, String parentType, ReactionType reactionType);
}