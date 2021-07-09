package com.meli.socialmeli.controller;

import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.form.PostForm;
import com.meli.socialmeli.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = {"/products/newpost", "/products/newpromopost"})
    @ResponseStatus(HttpStatus.CREATED)
    public Post save(@Valid @RequestBody PostForm postForm) {
        return postService.save(postForm);
    }
}
