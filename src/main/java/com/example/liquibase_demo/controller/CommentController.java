package com.example.liquibase_demo.controller;

import com.example.liquibase_demo.entity.Comment;
import com.example.liquibase_demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @GetMapping("/{id}")
    public Optional<Comment> getCommentById(@PathVariable Integer id) {
        return commentService.getCommentById(id);
    }

    @GetMapping("/by-parent")
    public List<Comment> getCommentsByParent(
            @RequestParam Integer parentId,
            @RequestParam String parentType) {
        return commentService.getCommentsByParent(parentId, parentType);
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @PutMapping("/{id}")
    public Comment updateComment(@PathVariable Integer id, @RequestBody Comment updatedComment) {
        return commentService.updateComment(id, updatedComment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
    }
}