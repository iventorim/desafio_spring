package com.meli.socialmeli.service;

import com.meli.socialmeli.dto.CountPromoSellerDTO;
import com.meli.socialmeli.dto.FollowersDTO;
import com.meli.socialmeli.dto.FollowersSellerDTO;
import com.meli.socialmeli.dto.ListPromoProdSellerDTO;
import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
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
                .orElseThrow(() -> new NoSuchElementException("Não foi encontrado nenhum usuário vendedor com o id: "+idSeller));
        List<Post> postsSellerPromotions = this.getPostsSellerPromotions(seller);
        return new CountPromoSellerDTO(seller.getUserId(),seller.getUsername(),postsSellerPromotions.size());
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

        if(Objects.nonNull(order)) {
            postsSellerPromotions.sort((p1, p2) -> {
                if (order.equals("name_asc")) {
                    return p2.getDetail().getProductName().compareToIgnoreCase(p1.getDetail().getProductName());
                } else if (order.equals("name_desc")) {
                    return p1.getDetail().getProductName().compareToIgnoreCase(p2.getDetail().getProductName());
                }
                return 0;
            });
        }
        return postsSellerPromotions;
    }

    public FollowersSellerDTO getFollowersSellerCount(Integer sellerId) {

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new NoSuchElementException("Não foi encontrado nenhum usuário vendedor com o id: "+sellerId));

        return FollowersSellerDTO.convert(seller);
    }

    public FollowersDTO getFollowers(Integer userID) {
        return FollowersDTO.convert(sellerRepository.getById(userID));
    }
}
