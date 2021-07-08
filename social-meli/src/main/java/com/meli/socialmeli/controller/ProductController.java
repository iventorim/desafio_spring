package com.meli.socialmeli.controller;


import com.meli.socialmeli.dto.CountPromoSellerDTO;
import com.meli.socialmeli.dto.ListPromoProdSellerDTO;
import com.meli.socialmeli.service.SellerService;
import com.meli.socialmeli.dto.UserFollowingPostsDTO;
import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final SellerService sellerService;

    private final ClientService clientService;

    @Autowired
    public ProductController(SellerService sellerService, ClientService clientService) {
        this.sellerService = sellerService;
        this.clientService = clientService;
    }

    @GetMapping("{userId}/countPromo")
    @ResponseStatus(HttpStatus.OK)
    public CountPromoSellerDTO getCountPromoSeller(@PathVariable Integer userId) {
        return sellerService.getCountPostPromoSeller(userId);
    }

    @GetMapping("{userId}/list")
    @ResponseStatus(HttpStatus.OK)
    public ListPromoProdSellerDTO getListPromoProdSeller(@PathVariable Integer userId, @RequestParam(required = false) String order) {
        return sellerService.getListPromoProdSeller(userId, order);
    }

    @GetMapping("/followed/{userId}/list")
    @ResponseStatus(HttpStatus.OK)
    public UserFollowingPostsDTO listSellersPostsByUserId(@PathVariable int userId, @RequestParam(required = false) String order) {
        List<Post> posts = clientService.getUserFollowingSellersPosts(userId, order);

        return new UserFollowingPostsDTO(userId, posts);
    }
}
