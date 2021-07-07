package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.FollowersDTO;
import com.meli.socialmeli.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }


    public FollowersDTO getFollowers(Integer userID) {

        return FollowersDTO.convert(sellerRepository.getById(userID));

    }
}
