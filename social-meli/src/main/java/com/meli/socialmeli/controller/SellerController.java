package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.FollowersCountDTO;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.meli.socialmeli.dto.FollowersListDTO;
import org.springframework.beans.factory.annotation.Autowired;

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

    @PostMapping("/users/newseller")
    @ResponseStatus(HttpStatus.OK)
    public void newSeller(@RequestBody Seller seller){
        sellerService.addSeller(seller);
    }

    @PutMapping("/users/{userId}/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateSeller(@PathVariable int userId, @RequestBody Seller seller){
        sellerService.updateSeller(userId, seller);
    }

    @DeleteMapping("/users/{userId}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSeller(@PathVariable int userId){
        sellerService.deleteSeller(userId);
    }
    
}
