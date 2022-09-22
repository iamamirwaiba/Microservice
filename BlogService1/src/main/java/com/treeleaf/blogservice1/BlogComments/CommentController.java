package com.treeleaf.blogservice1.BlogComments;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<Map<String,String>> addBlog(@RequestBody Comment comment) throws Exception {
        return commentService.addComment(comment);
    }

    @GetMapping("/getbyId/{id}")
    public @ResponseBody ResponseEntity<Comment> addBlog(@PathVariable int id) throws Exception {
        return commentService.getComment(id);
    }

    @PostMapping("/deletebyId/{id}")
    public @ResponseBody ResponseEntity<Map<String, String>> deleteBlog(@PathVariable int id) throws Exception {
        return commentService.deleteComment(id);
    }

    @GetMapping("/deletebyId/{id}")
    public @ResponseBody ResponseEntity<Map<String, String>> updateBlog(@PathVariable int id,@RequestBody Comment comment) throws Exception {
        return commentService.updateComment(id,comment);
    }


}
