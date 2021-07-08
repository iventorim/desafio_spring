package com.meli.socialmeli.dto;

import com.meli.socialmeli.entity.Seller;

public class FollowersCountDTO {

    private final Integer userId;

    private final String userName;

    private final Integer followers_count;

    public FollowersCountDTO(Integer userId, String userName, Integer followers_count) {
        this.userId = userId;
        this.userName = userName;
        this.followers_count = followers_count;
    }

    public FollowersCountDTO(Seller seller) {
        this.userId = seller.getUserId();
        this.userName = seller.getUsername();
        this.followers_count = seller.getFollowers().size();
    }

    public static FollowersCountDTO convert(Seller seller) {
        return new FollowersCountDTO(seller);
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getFollowers_count() {
        return followers_count;
    }

}
