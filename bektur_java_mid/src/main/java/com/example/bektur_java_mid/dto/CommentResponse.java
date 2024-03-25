package com.example.bektur_java_mid.dto;

import com.example.bektur_java_mid.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponse {
    private Long id;

    private String userEmail;

    private LocalDateTime time;
    private String comment;
}

