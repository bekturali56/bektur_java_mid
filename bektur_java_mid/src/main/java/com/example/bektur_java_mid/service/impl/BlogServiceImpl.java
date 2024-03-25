package com.example.bektur_java_mid.service.impl;

import com.example.bektur_java_mid.dto.BlogRequest;
import com.example.bektur_java_mid.dto.BlogResponse;
import com.example.bektur_java_mid.dto.CommentRequest;
import com.example.bektur_java_mid.dto.CommentResponse;
import com.example.bektur_java_mid.entity.Blog;
import com.example.bektur_java_mid.entity.Comment;
import com.example.bektur_java_mid.entity.User;
import com.example.bektur_java_mid.exception.NotFoundException;
import com.example.bektur_java_mid.mapper.BlogMapper;
import com.example.bektur_java_mid.mapper.CommentMapper;
import com.example.bektur_java_mid.repo.BlogRepository;
import com.example.bektur_java_mid.repo.CommentRepository;
import com.example.bektur_java_mid.repo.UserRepository;
import com.example.bektur_java_mid.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    @Override
    public void create(BlogRequest request) {
        Blog blog = new Blog();
        blog.setUrl(request.getUrl());
        blog.setDescription(request.getDescription());
        blog.setName(request.getName());
        blogRepository.save(blog);
    }

    @Override
    public List<BlogResponse> getAll() {
        return blogMapper.toDtoS(blogRepository.findAll());
    }

    @Override
    public BlogResponse byId(Long blogId) {
        Optional<Blog> blog = blogRepository.findById(blogId);
        if (blog.isEmpty())
            throw new NotFoundException("not found blog with id: "+ blogId, HttpStatus.NOT_FOUND);
        return blogMapper.toDto(blog.get());
    }

    @Override
    public String likeBlog(Long userId, Long blogId) {
        Optional<Blog> blog = blogRepository.findById(blogId);
        String result;
        if (blog.isEmpty())
            throw new NotFoundException("not found blog with id: "+ blogId, HttpStatus.NOT_FOUND);
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty())
            throw new NotFoundException("not found user with id: "+ userId, HttpStatus.NOT_FOUND);
        List<User> blogLikedUsers = blog.get().getLikedUsers();
        if (blogLikedUsers.contains(user.get())){
            blogLikedUsers.remove(user.get());
            result = "was like -> unlike";
        }
        else {
            blogLikedUsers.add(user.get());
            result = "was unlike -> like";
        }
        return result;
    }

    @Override
    public void commentBlog(CommentRequest request, Long blogId) {
        Optional<Blog> blog = blogRepository.findById(blogId);
        if (blog.isEmpty())
            throw new NotFoundException("not found blog with id: "+ blogId, HttpStatus.NOT_FOUND);
        Optional<User> user = userRepository.findByEmail(request.getUserEmail());
        if (user.isEmpty())
            throw new NotFoundException("not found user with email: "+ request.getUserEmail(), HttpStatus.NOT_FOUND);

        List<Comment> blogComments = blog.get().getComments();
        Comment comment = new Comment();
        comment.setUser(user.get());
        comment.setTime(LocalDateTime.now());
        comment.setComment(request.getComment());
        commentRepository.save(comment);
        blogComments.add(comment);
        blog.get().setComments(blogComments);
        blogRepository.save(blog.get());

    }

    @Override
    public List<CommentResponse> getBlogComments(Long blogId) {
        Optional<Blog> blog = blogRepository.findById(blogId);
        if (blog.isEmpty())
            throw new NotFoundException("not found blog with id: "+ blogId, HttpStatus.NOT_FOUND);
        return commentMapper.toDtoS(blog.get().getComments());
    }
}
