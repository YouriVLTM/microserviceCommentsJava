package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.queue.QueueService;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import java.util.UUID;

@RestController
public class CommentController {

    @Value("${queue.name}")
    private String queueName;

    @Autowired
    private QueueService queueService;

    @Autowired
    private CommentRepository commentRepository;


    /* ActiveMQ */
    @ResponseBody
    @RequestMapping(value="/metrics", produces="text/plain")
    public String metrics() {
        int totalMessages = queueService.pendingJobs(queueName);
        return "# HELP messages Number of messages in the queueService\n"
                + "# TYPE messages gauge\n"
                + "messages " + totalMessages;
    }

    @PostMapping("/submit")
    public String submit() {
        for (long i = 0; i < 100; i++) {
            String id = UUID.randomUUID().toString();
            queueService.send(queueName, id);
        }
        return "success";
    }


/*Rest Controller*/

    @GetMapping("/comments")
    public List<Comment> getComments()
    {
       String id = UUID.randomUUID().toString();
        queueService.send(queueName, id);
        return commentRepository.findAll();
    }

    @GetMapping("/comments/{key}")
    public Comment getComment(@PathVariable String key)    {
        return commentRepository.findByKey(key);
    }

    @PostMapping("/comments")
    Comment addComment(@RequestBody Comment newComment)
    {
        // create date and update date
        newComment.setCreateDate(new Date(System.currentTimeMillis()));
        newComment.setUpdateDate(new Date(System.currentTimeMillis()));
        commentRepository.save(newComment);
        return newComment;
    }

    @PutMapping("/comments")
    public Comment updateComment(@RequestBody Comment updateComment)
    {
        Comment comment = commentRepository.findByKey(updateComment.getKey());
        if(comment != null){
            comment.setTitle(updateComment.getTitle());
            comment.setDescription(updateComment.getDescription());
            comment.setUserEmail(updateComment.getUserEmail());
            comment.setImageKey(updateComment.getImageKey());
            comment.setUpdateDate(new Date(System.currentTimeMillis()));
            commentRepository.save(comment);
            return comment;
        }else{
            return null;
        }

    }

    @DeleteMapping("/comments/{key}")
    public ResponseEntity<Object> deleteComment(@PathVariable String key)
    {
        Comment comment = commentRepository.findByKey(key);
        if(comment != null){
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }



}
