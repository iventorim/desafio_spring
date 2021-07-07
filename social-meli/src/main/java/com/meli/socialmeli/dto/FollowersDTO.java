package com.meli.socialmeli.dto;

import com.meli.socialmeli.entity.Client;
import com.meli.socialmeli.entity.Seller;

import java.util.List;
import java.util.stream.Collectors;

public class FollowersDTO {

    private Integer userId;
    private String username;
    private List<Client> followers;

    public FollowersDTO(Integer userId, String username, List<Client> followers) {
        this.userId = userId;
        this.username = username;
        this.followers = followers;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public List<Client> getFollowers() {
        return followers;
    }

    public static FollowersDTO convert(Seller seller){
        return new FollowersDTO(seller.getUserId(),
                seller.getUsername(),
                seller.getFollowers()
        );
    }
}
