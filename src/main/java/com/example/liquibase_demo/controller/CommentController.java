package com.example.liquibase_demo.controller;

import com.example.liquibase_demo.dto.CommentDTO;
import com.example.liquibase_demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public CommentDTO createComment(@RequestBody CommentDTO dto) {
        return commentService.saveComment(dto);
    }

    @PutMapping("/{id}")
    public CommentDTO updateComment(@PathVariable Integer id, @RequestBody CommentDTO dto) {
        return commentService.updateComment(id, dto);
    }

    @GetMapping
    public List<CommentDTO> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public CommentDTO getCommentById(@PathVariable Integer id) {
        return commentService.getCommentById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
    }
}