package com.example.liquibase_demo.repository;

import com.example.liquibase_demo.entity.PostMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostMediaRepository extends JpaRepository<PostMedia, Integer> {

    List<PostMedia> findByPostId(Integer postId);

    
}