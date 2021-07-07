package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.FollowersDTO;
import com.meli.socialmeli.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SellerController {

    private final SellerService sellerService;


    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/users/{userID}/followers/list")
    public FollowersDTO followersList(@PathVariable int userID){
        return sellerService.getFollowers(userID);
    }
}
