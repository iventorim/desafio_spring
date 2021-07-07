package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.FollowersSellerDTO;
import com.meli.socialmeli.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/users/{userId}/followers/count")
    @ResponseStatus(value = HttpStatus.OK)
    public FollowersSellerDTO getFollowersSellerCount(@PathVariable Integer userId) {

        return sellerService.getFollowersSellerCount(userId);
    }

}
