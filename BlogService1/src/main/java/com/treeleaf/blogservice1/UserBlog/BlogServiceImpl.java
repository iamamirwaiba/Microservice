package com.treeleaf.blogservice1.UserBlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    private BlogRepo blogRepo;

    @Autowired
    BlogServiceImpl(BlogRepo blogRepo){
        this.blogRepo=blogRepo;
    }



    @Override
    public ResponseEntity<Map<String, String>> addBlog(Blog blog, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String,String> service=new HashMap<>();
        System.out.println(response.getStatus());

        try {
            blogRepo.save(blog);
        }
        catch (Exception e){
            throw new Exception("Failed to save blog");
        }

        service.put("message","successfully Added");
        return new ResponseEntity<>(service, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Blog> getBlog(int id) throws Exception {
        try {
            return new ResponseEntity<>(blogRepo.findById(id).get(), HttpStatus.OK);
        }

        catch (Exception e){
            throw new Exception("failed to get blog");
        }
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, String>> updateBlog(int id,Blog blog) throws Exception {
        Map<String,String> service=new HashMap<>();
        try {
            blogRepo.updateBlog(id,blog.getPost());
            service.put("message","updated successfully");
        }
        catch (Exception e){
            throw new Exception("failed to update Blog");
        }
        return new ResponseEntity<>(service,HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, String>> deleteBlog(int id) throws Exception {
        Map<String,String> service=new HashMap<>();
        try{
            blogRepo.deleteById(id);
            service.put("message","deleted succcessfully");
        }
        catch (Exception e){
            throw new Exception("failed to delete blog");
        }
        return new ResponseEntity<>(service,HttpStatus.OK);


    }
}
