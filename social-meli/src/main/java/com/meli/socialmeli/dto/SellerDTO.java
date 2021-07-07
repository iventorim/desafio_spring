package com.meli.socialmeli.dto;

import com.meli.socialmeli.entity.Seller;

import java.util.List;
import java.util.stream.Collectors;

public class SellerDTO implements Comparable<SellerDTO> {
    private String username;
    private Integer userId;

    // constructors

    public SellerDTO(String username, Integer userId) {
        this.username = username;
        this.userId = userId;
    }

    public SellerDTO(Seller seller) {
        this.username = seller.getUsername();
        this.userId = seller.getUserId();
    }

    // DTO methods

    public static SellerDTO converter(Seller seller) {
        return new SellerDTO(seller.getUsername(), seller.getUserId());
    }

    public static List<SellerDTO> converter(List<Seller> sellers) {
        return sellers.stream()
                .map(s -> SellerDTO.converter(s))
                .collect(Collectors.toList());
    }

    // getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public int compareTo(SellerDTO compareSeller) {
        return this.username.compareToIgnoreCase(compareSeller.getUsername());
    }
}
