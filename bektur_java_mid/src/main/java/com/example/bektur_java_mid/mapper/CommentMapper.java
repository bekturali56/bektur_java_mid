package com.example.bektur_java_mid.mapper;

import com.example.bektur_java_mid.dto.CommentResponse;
import com.example.bektur_java_mid.entity.Comment;

import java.util.List;

public interface CommentMapper {
    List<CommentResponse> toDtoS(List<Comment> comments);
}
