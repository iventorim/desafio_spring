package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.CountPromoSellerDTO;
import com.meli.socialmeli.dto.FollowersListDTO;
import com.meli.socialmeli.dto.FollowersCountDTO;
import com.meli.socialmeli.dto.ListPromoProdSellerDTO;
import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public CountPromoSellerDTO getCountPostPromoSeller(Integer idSeller) {

        Optional<Seller> optionalSeller = sellerRepository.findById(idSeller);
        if(optionalSeller.isPresent()) {
            Seller seller = optionalSeller.get();
            List<Post> postsSellerPromotions = this.getPostsSellerPromotions(seller);
            return new CountPromoSellerDTO(seller.getUserId(),seller.getUsername(),postsSellerPromotions.size());
        }

        throw new NoSuchElementException("Não foi encontrado nenhum usuário vendedor com o id: "+idSeller);
    }

    private List<Post> getPostsSellerPromotions(Seller seller) {
        return seller
                .getPosts()
                .stream()
                .filter(Post::isHasPromo)
                .collect(Collectors.toList());
    }

    public ListPromoProdSellerDTO getListPromoProdSeller(Integer idSeller) {

        Optional<Seller> optionalSeller = sellerRepository.findById(idSeller);

        if (optionalSeller.isPresent()) {
            Seller seller = optionalSeller.get();
            List<Post> postsSellerPromotions = this.getPostsSellerPromotions(seller);
            return new ListPromoProdSellerDTO(seller.getUserId(), seller.getUsername(), postsSellerPromotions);
        }

        throw new NoSuchElementException("Não foi encontrado nenhum usuário vendedor com o id: " + idSeller);
    }

    public FollowersCountDTO getFollowersSellerCount(Integer sellerId) {

        Optional<Seller> optionalSeller = sellerRepository.findById(sellerId);

        if(optionalSeller.isPresent()) {
            return FollowersCountDTO.convert(optionalSeller.get());
        }

        throw new NoSuchElementException("Não foi encontrado nenhum usuário vendedor com o id: "+sellerId);
    }

    public FollowersListDTO getFollowers(Integer userID) {
        return FollowersListDTO.convert(sellerRepository.getById(userID));
    }
}
