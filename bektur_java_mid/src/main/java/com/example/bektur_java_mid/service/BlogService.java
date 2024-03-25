package com.example.bektur_java_mid.service;

import com.example.bektur_java_mid.dto.BlogRequest;
import com.example.bektur_java_mid.dto.BlogResponse;
import com.example.bektur_java_mid.dto.CommentRequest;
import com.example.bektur_java_mid.dto.CommentResponse;

import java.util.List;

public interface BlogService {
    void create(BlogRequest request);

    List<BlogResponse> getAll();

    BlogResponse byId(Long blogId);

    String likeBlog(Long userId, Long blogId);

    void commentBlog(CommentRequest request, Long blogId);

    List<CommentResponse> getBlogComments(Long blogId);
}
