package com.example.liquibase_demo.service;

import com.example.liquibase_demo.entity.Comment;
import com.example.liquibase_demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public Optional<Comment> getCommentById(Integer id) {
        return commentRepository.findById(id);
    }

    public List<Comment> getCommentsByParent(Integer parentId, String parentType) {
        return commentRepository.findByParentIdAndParentType(parentId, parentType);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment updateComment(Integer id, Comment updated) {
        Comment existing = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id " + id));

        existing.setCommentText(updated.getCommentText());
        existing.setUpdatedAt(LocalDateTime.now());
        return commentRepository.save(existing);
    }

    public void deleteComment(Integer id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id " + id));

        comment.setDeletedAt(LocalDateTime.now());
        commentRepository.save(comment); // Soft delete
    }
}