package com.meli.socialmeli.service;


import com.meli.socialmeli.dto.FollowersSellerDTO;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.dto.FollowersDTO;
import com.meli.socialmeli.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SellerService {


    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }


    public FollowersSellerDTO getFollowersSellerCount(Integer sellerId) {

        Optional<Seller> optionalSeller = sellerRepository.findById(sellerId);

        if(optionalSeller.isPresent()) {
            return FollowersSellerDTO.convert(optionalSeller.get());
        }

        throw new NoSuchElementException("Não foi encontrado nenhum usuário vendedor com o id: "+sellerId);
    }

    public FollowersDTO getFollowers(Integer userID) {
        return FollowersDTO.convert(sellerRepository.getById(userID));
    }
}
