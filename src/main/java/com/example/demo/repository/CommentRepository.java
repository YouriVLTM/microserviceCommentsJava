package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Comment;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "comments", path = "comments")
public interface CommentRepository extends MongoRepository<Comment, String> {
    Comment findByKey(@Param("key") String key);
    List<Comment> findByImageKey(@Param("imageKey") String imageKey);
    List<Comment> findByUserEmail(@Param("userEmail") String userEmail);
}