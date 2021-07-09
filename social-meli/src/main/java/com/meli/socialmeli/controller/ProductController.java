package com.meli.socialmeli.controller;


import com.meli.socialmeli.dto.CountPromoSellerDTO;
import com.meli.socialmeli.dto.ListPromoProdSellerDTO;
import com.meli.socialmeli.entity.Product;
import com.meli.socialmeli.service.ProductService;
import com.meli.socialmeli.service.SellerService;
import com.meli.socialmeli.dto.UserFollowingPostsDTO;
import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final SellerService sellerService;
    private final ClientService clientService;
    private final ProductService productService;

    @Autowired
    public ProductController(SellerService sellerService, ClientService clientService, ProductService productService) {
        this.sellerService = sellerService;
        this.clientService = clientService;
        this.productService = productService;
    }

    @GetMapping("{userId}/countPromo")
    @ResponseStatus(HttpStatus.OK)
    public CountPromoSellerDTO getCountPromoSeller(@PathVariable int userId) {
        return sellerService.getCountPostPromoSeller(userId);
    }

    @GetMapping("{userId}/list")
    @ResponseStatus(HttpStatus.OK)
    public ListPromoProdSellerDTO getListPromoProdSeller(@PathVariable int userId, @RequestParam(required = false) String order) {
        return sellerService.getListPromoProdSeller(userId, order);
    }

    @GetMapping("followed/{userId}/list")
    @ResponseStatus(HttpStatus.OK)
    public UserFollowingPostsDTO listSellersPostsByUserId(@PathVariable int userId, @RequestParam(required = false) String order) {
        List<Post> posts = clientService.getUserFollowingSellersPosts(userId, order);

        return new UserFollowingPostsDTO(userId, posts);
    }


    @PostMapping("/newProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProduct(@RequestBody Product product){
        productService.createNewProduct(product);
    }
}
