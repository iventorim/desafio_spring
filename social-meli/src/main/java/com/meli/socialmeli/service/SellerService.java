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
import java.util.stream.Collectors;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public CountPromoSellerDTO getCountPostPromoSeller(Integer idSeller) {

        Seller seller = sellerRepository.findById(idSeller)
                .orElseThrow(() -> new NoSuchElementException("Não foi encontrado nenhum usuário vendedor com o id: " + idSeller));
        List<Post> postsSellerPromotions = this.getPostsSellerPromotions(seller);
        return new CountPromoSellerDTO(seller.getUserId(), seller.getUsername(), postsSellerPromotions.size());
    }

    private List<Post> getPostsSellerPromotions(Seller seller) {
        return seller
                .getPosts()
                .stream()
                .filter(Post::isHasPromo)
                .collect(Collectors.toList());
    }

    public ListPromoProdSellerDTO getListPromoProdSeller(Integer idSeller, String order) {

        Seller seller = sellerRepository.findById(idSeller).orElseThrow(() -> new NoSuchElementException("Não foi encontrado nenhum usuário vendedor com o id: " + idSeller));

        List<Post> postsSellerPromotions = this.getPostsSellerPromotions(seller);
        List<Post> postsSellerPromotionsSorted = this.sortListPostPromotionsSeller(postsSellerPromotions, order);
        return new ListPromoProdSellerDTO(seller.getUserId(), seller.getUsername(), postsSellerPromotionsSorted);
    }

    private List<Post> sortListPostPromotionsSeller(List<Post> postsSellerPromotions, String order) {

        postsSellerPromotions.sort((p1, p2) ->
                new StringComparator(order).compare(p1.getDetail().getProductName(),p2.getDetail().getProductName()));

        return postsSellerPromotions;
    }

    public FollowersCountDTO getFollowersSellerCount(Integer sellerId) {

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new NoSuchElementException("Não foi encontrado nenhum usuário vendedor com o id: " + sellerId));

        return FollowersCountDTO.convert(seller);
    }

    public FollowersListDTO getFollowers(Integer userId,String order) {

        Seller seller = sellerRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Não foi encontrado nenhum usuário vendedor com o id: " + userId));

        seller.getFollowers().sort((s1, s2) -> new StringComparator(order).compare(s1.getUsername(),s2.getUsername()));

        return FollowersListDTO.convert(seller);

    }
}
