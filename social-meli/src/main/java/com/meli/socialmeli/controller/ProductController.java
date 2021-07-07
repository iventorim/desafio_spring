package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.UserFollowingPostsDTO;
import com.meli.socialmeli.service.ClientService;
import com.meli.socialmeli.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;
    private final ClientService clientService;

    @Autowired
    public ProductController(ProductService service, ClientService clientService) {
        this.service = service;
        this.clientService = clientService;
    }

    @GetMapping("/followed/{userId}/list")
    @ResponseStatus(HttpStatus.OK)
    public UserFollowingPostsDTO listSellersPostsByUserId(@PathVariable int userId) {
        return new UserFollowingPostsDTO(userId, clientService.getUserFollowingSellersPosts(userId));
    }
}
