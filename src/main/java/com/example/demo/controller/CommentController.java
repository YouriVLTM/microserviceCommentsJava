package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comments")
    public List<Comment> getComments()
    {
        return commentRepository.findAll();
    }

    @GetMapping("/comments/{title}")
    public Comment getComment(@PathVariable String title)
    {
        return commentRepository.findByTitle(title);
    }

    @PostMapping("/comments")
    Comment addComment(@RequestBody Comment newComment)
    {
        //DOTO check if title exist
        commentRepository.save(newComment);
        return newComment;
    }

    @PutMapping("/comments")
    public Comment updateComment(@RequestBody Comment updateComment)
    {
        Comment comment = commentRepository.findByTitle(updateComment.getTitle());
        comment.setSubTitle(updateComment.getSubTitle());
        comment.setDescription(updateComment.getDescription());
        comment.setUserEmail(updateComment.getUserEmail());
        commentRepository.save(comment);
        return comment;
    }

    @DeleteMapping("/comments/{title}")
    public ResponseEntity<Object> deleteComment(@PathVariable String title)
    {
        Comment comment = commentRepository.findByTitle(title);
        if(comment != null){
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }



}
