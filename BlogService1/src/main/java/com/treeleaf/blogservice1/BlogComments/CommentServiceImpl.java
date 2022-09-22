package com.treeleaf.blogservice1.BlogComments;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;



    @Override
    public ResponseEntity<Map<String, String>> addComment(Comment comment) throws Exception {
        Map<String,String> service=new HashMap<>();
        try {
            commentRepo.save(comment);
        }
        catch (Exception e){
            throw new Exception("Failed to save comment");
        }

        service.put("message","successfully Added");
        return new ResponseEntity<>(service, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Comment> getComment(int id) throws Exception {
        try {
            return new ResponseEntity<>(commentRepo.findById(id).get(), HttpStatus.OK);
        }

        catch (Exception e){
            throw new Exception("failed to get blog");
        }
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, String>> updateComment(int id,Comment comment) throws Exception {
        Map<String,String> service=new HashMap<>();
        try {
            commentRepo.updateBlog(id,comment.getComment());
            service.put("message","updated successfully");
        }
        catch (Exception e){
            throw new Exception("failed to update Blog");
        }
        return new ResponseEntity<>(service,HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, String>> deleteComment(int id) throws Exception {
        Map<String,String> service=new HashMap<>();
        try{
            commentRepo.deleteById(id);
            service.put("message","deleted succcessfully");
        }
        catch (Exception e){
            throw new Exception("failed to delete blog");
        }
        return new ResponseEntity<>(service,HttpStatus.OK);


    }
}
