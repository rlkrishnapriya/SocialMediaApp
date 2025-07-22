package com.example.liquibase_demo.repository;

import com.example.liquibase_demo.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByUserId(Integer userId);

    List<Comment> findByParentIdAndParentType(Integer parentId, String parentType);

    List<Comment> findByParentIdAndParentTypeOrderByCreatedAtAsc(Integer parentId, String parentType);

    List<Comment> findByCommentTextContainingIgnoreCase(String keyword);
}