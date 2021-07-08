package com.meli.socialmeli.dto;

import com.meli.socialmeli.entity.Post;

import java.util.List;

public class ListPromoProdSellerDTO {

    private final Integer userId;

    private final String userName;

    private final List<Post> posts;

    public ListPromoProdSellerDTO(Integer userId, String userName, List<Post> posts) {
        this.userId = userId;
        this.userName = userName;
        this.posts = posts;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public List<Post> getPosts() {
        return posts;
    }
}
