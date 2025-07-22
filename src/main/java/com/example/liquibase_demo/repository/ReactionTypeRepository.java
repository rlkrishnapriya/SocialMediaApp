package com.example.liquibase_demo.repository;

import com.example.liquibase_demo.entity.ReactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReactionTypeRepository extends JpaRepository<ReactionType, Integer> {

    Optional<ReactionType> findByType(String type);

    boolean existsByType(String type);
}
