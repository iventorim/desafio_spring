package com.meli.socialmeli.dto;

import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.entity.Seller;

import java.util.List;

public class CountPromoSellerDTO {

    private final Integer userId;
    private final String userName;
    private final Integer promoproducts_count;

    public CountPromoSellerDTO(Integer userId, String userName, Integer promoproducts_count) {
        this.userId = userId;
        this.userName = userName;
        this.promoproducts_count = promoproducts_count;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getPromoproducts_count() {
        return promoproducts_count;
    }
}
