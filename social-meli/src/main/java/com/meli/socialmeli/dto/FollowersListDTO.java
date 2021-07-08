package com.meli.socialmeli.dto;

import com.meli.socialmeli.entity.Client;
import com.meli.socialmeli.entity.Seller;

import java.util.List;

public class FollowersListDTO {

    private Integer userId;
    private String username;
    private List<Client> followers;

    public FollowersListDTO(Integer userId, String username, List<Client> followers) {
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

    public static FollowersListDTO convert(Seller seller) {
        return new FollowersListDTO(seller.getUserId(),
                seller.getUsername(),
                seller.getFollowers()
        );
    }
}
