package com.example.bektur_java_mid.mapper.impl;

import com.example.bektur_java_mid.dto.BlogResponse;
import com.example.bektur_java_mid.entity.Blog;
import com.example.bektur_java_mid.mapper.BlogMapper;
import com.example.bektur_java_mid.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BlogMapperImpl  implements BlogMapper {
    private final CommentMapper commentMapper;
    @Override
    public List<BlogResponse> toDtoS(List<Blog> all) {
        List<BlogResponse> blogResponses = new ArrayList<>();
        for (Blog blog: all)
            blogResponses.add((toDto(blog)));
        return blogResponses;
    }

    @Override
    public BlogResponse toDto(Blog blog) {
        BlogResponse blogResponse = new BlogResponse();
        blogResponse.setId(blog.getId());
        blogResponse.setUrl(blog.getUrl());
        blogResponse.setName(blog.getName());
        blogResponse.setDescription(blog.getDescription());
        blogResponse.setLikeCount(blog.getLikedUsers()!=null? blog.getLikedUsers().size(): 0);
        blogResponse.setComments(blog.getComments()!=null? commentMapper.toDtoS(blog.getComments()): null);
        return blogResponse;
    }
}
