package com.example.bektur_java_mid.mapper;

import com.example.bektur_java_mid.dto.BlogResponse;
import com.example.bektur_java_mid.entity.Blog;

import java.util.List;

public interface BlogMapper {
    List<BlogResponse> toDtoS(List<Blog> all);

    BlogResponse toDto(Blog blog);
}
