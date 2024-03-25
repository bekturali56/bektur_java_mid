package com.example.bektur_java_mid.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BlogResponse {
    private Long id;
    private String url;
    private String name;
    private String description;

    private Integer likeCount;

    private List<CommentResponse> comments;
}
