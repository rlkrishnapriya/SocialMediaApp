package com.example.liquibase_demo.repository;

import com.example.liquibase_demo.entity.PostMedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMediaRepository extends JpaRepository<PostMedia, Integer> {
}
