package com.treeleaf.blogservice1.UserBlog;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface BlogService {
    ResponseEntity<Map<String,String>> addBlog(Blog blog, HttpServletRequest request, HttpServletResponse response) throws Exception;
    ResponseEntity<Blog> getBlog(int id) throws Exception;
    ResponseEntity<Map<String,String>> updateBlog(int id,Blog blog) throws Exception;
    ResponseEntity<Map<String,String>> deleteBlog(int id) throws Exception;
}
