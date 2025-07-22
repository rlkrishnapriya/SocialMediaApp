package com.example.liquibase_demo.repository;

import com.example.liquibase_demo.entity.GroupRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRoleRepository extends JpaRepository<GroupRole, Integer> {

    Optional<GroupRole> findByName(String name);

    boolean existsByName(String name);
}