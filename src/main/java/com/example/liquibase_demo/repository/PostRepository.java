package com.example.liquibase_demo.repository;

import com.example.liquibase_demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
