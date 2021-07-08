package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.FollowersCountDTO;
import com.meli.socialmeli.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.meli.socialmeli.dto.FollowersListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }


    @GetMapping("/users/{userId}/followers/count")
    @ResponseStatus(value = HttpStatus.OK)
    public FollowersCountDTO getFollowersSellerCount(@PathVariable Integer userId) {

        return sellerService.getFollowersSellerCount(userId);
    }

    @GetMapping("/users/{userID}/followers/list")
    public FollowersListDTO followersList(@PathVariable int userID){
        return sellerService.getFollowers(userID);
    }

}
