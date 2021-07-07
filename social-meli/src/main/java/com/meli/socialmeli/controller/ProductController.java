package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.CountPromoSellerDTO;
import com.meli.socialmeli.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final SellerService sellerService;

    @Autowired
    public ProductController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("products/{userId}/countPromo")
    @ResponseStatus(HttpStatus.OK)
    public CountPromoSellerDTO getCountPromoSeller(@PathVariable Integer userId) {
        return sellerService.getCountPostPromoSeller(userId);
    }

}
