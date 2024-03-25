package com.example.bektur_java_mid.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentRequest {

    private String userEmail;

    private String comment;
}
