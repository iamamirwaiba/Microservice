package com.treeleaf.blogservice1.BlogComments;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CommentService {
    ResponseEntity<Map<String,String>> addComment(Comment comment) throws Exception;
    ResponseEntity<Comment> getComment(int id) throws Exception;
    ResponseEntity<Map<String,String>> updateComment(int id,Comment comment) throws Exception;
    ResponseEntity<Map<String,String>> deleteComment(int id) throws Exception;
}
