package com.example.bektur_java_mid.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String name;
    private String description;

    @ManyToMany
    private List<User> likedUsers;

    @OneToMany
    private List<Comment> comments;
}
