package com.example.liquibase_demo.service;

import com.example.liquibase_demo.dto.CommentDTO;
import com.example.liquibase_demo.entity.Comment;
import com.example.liquibase_demo.entity.User;
import com.example.liquibase_demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentDTO saveComment(CommentDTO dto) {
        Comment comment = new Comment();

        User user = new User();
        user.setId(dto.getUserId());
        comment.setUser(user);

        comment.setParentId(dto.getParentId());
        comment.setParentType(dto.getParentType());
        comment.setCommentText(dto.getComment());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(dto.getUpdatedAt());
        comment.setDeletedAt(dto.getDeletedAt());

        Comment saved = commentRepository.save(comment);
        return convertToDTO(saved);
    }

    public CommentDTO updateComment(Integer id, CommentDTO dto) {
        Optional<Comment> optional = commentRepository.findById(id);
        if (optional.isEmpty()) throw new RuntimeException("Comment not found");

        Comment comment = optional.get();
        comment.setCommentText(dto.getComment());
        comment.setUpdatedAt(LocalDateTime.now());
        comment.setDeletedAt(dto.getDeletedAt());

        return convertToDTO(commentRepository.save(comment));
    }

    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<CommentDTO> getCommentById(Integer id) {
        return commentRepository.findById(id).map(this::convertToDTO);
    }

    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }

    private CommentDTO convertToDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getUser().getId(),
                comment.getParentId(),
                comment.getParentType(),
                comment.getCommentText(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.getDeletedAt()
        );
    }
}