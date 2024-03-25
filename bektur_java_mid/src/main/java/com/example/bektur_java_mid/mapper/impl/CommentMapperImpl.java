package com.example.bektur_java_mid.mapper.impl;

import com.example.bektur_java_mid.dto.CommentResponse;
import com.example.bektur_java_mid.entity.Comment;
import com.example.bektur_java_mid.mapper.CommentMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapperImpl implements CommentMapper {
    @Override
    public List<CommentResponse> toDtoS(List<Comment> comments) {
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment: comments)
            commentResponses.add(toDto(comment));
        return commentResponses;
    }

    private CommentResponse toDto(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setTime(comment.getTime());
        commentResponse.setUserEmail(comment.getUser().getEmail());
        commentResponse.setComment(comment.getComment());
        return commentResponse;
    }
}
