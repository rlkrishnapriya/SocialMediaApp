package com.example.liquibase_demo.repository;

import com.example.liquibase_demo.entity.Group;
import com.example.liquibase_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    List<Group> findByCreatedBy(User user);

    boolean existsByDisplayName(String displayName);
}