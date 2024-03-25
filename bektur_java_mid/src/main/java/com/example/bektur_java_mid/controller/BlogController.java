package com.example.bektur_java_mid.controller;

import com.example.bektur_java_mid.dto.BlogRequest;
import com.example.bektur_java_mid.dto.BlogResponse;
import com.example.bektur_java_mid.dto.CommentRequest;
import com.example.bektur_java_mid.dto.CommentResponse;
import com.example.bektur_java_mid.service.BlogService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog")
public class BlogController {
    private final BlogService blogService;

    @PostMapping("/create")
    public void createBlog(@RequestBody BlogRequest request){
        blogService.create(request);
    }

    @GetMapping("/all")
    public List<BlogResponse> all(){
        return blogService.getAll();
    }
    @GetMapping("/{blogId}")
    public BlogResponse byId(@PathVariable Long blogId){
        return blogService.byId(blogId);
    }

    @PostMapping("/like/{userId}/{blogId}")
    public String likeBlog(@PathVariable Long userId, @PathVariable Long blogId){
        return blogService.likeBlog(userId, blogId);
    }
    @PostMapping("/comment/{blogId}")
    public void commentBlog(@RequestBody CommentRequest request, @PathVariable Long blogId){
         blogService.commentBlog(request, blogId);
    }
    @GetMapping("/comments/{blogId}")
    public List<CommentResponse> commentResponses(@PathVariable Long blogId){
        return blogService.getBlogComments(blogId);
    }

}

