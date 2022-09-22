package com.treeleaf.blogservice1.UserBlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private BlogService blogService;


    @Autowired
    BlogController(BlogService blogService){
        this.blogService=blogService;
    }

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<Map<String,String>> addBlog(@RequestBody Blog blog) throws Exception {
        return blogService.addBlog(blog);
    }

    @GetMapping("/getbyId/{id}")
    public @ResponseBody ResponseEntity<Blog> addBlog(@PathVariable int id) throws Exception {
        return blogService.getBlog(id);
    }

    @PostMapping("/deletebyId/{id}")
    public @ResponseBody ResponseEntity<Map<String, String>> deleteBlog(@PathVariable int id) throws Exception {
        return blogService.deleteBlog(id);
    }

    @GetMapping("/deletebyId/{id}")
    public @ResponseBody ResponseEntity<Map<String, String>> updateBlog(@PathVariable int id,@RequestBody Blog blog) throws Exception {
        return blogService.updateBlog(id,blog);
    }


}
