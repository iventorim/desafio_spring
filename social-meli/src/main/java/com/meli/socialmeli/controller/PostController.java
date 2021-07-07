package com.meli.socialmeli.controller;

import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.form.PostForm;
import com.meli.socialmeli.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/products/newpost")
    public void save(@RequestBody PostForm postForm){
        postService.save(postForm);
    }

}
