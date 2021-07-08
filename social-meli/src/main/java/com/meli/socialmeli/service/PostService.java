package com.meli.socialmeli.service;

import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.form.PostForm;
import com.meli.socialmeli.repository.PostRepository;
import com.meli.socialmeli.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public PostService(PostRepository postRepository, SellerRepository sellerRepository) {
        this.postRepository = postRepository;
        this.sellerRepository = sellerRepository;
    }

    public void save(PostForm postForm){

        Seller seller = sellerRepository.getById(postForm.getUserId());
        postForm.setSeller(seller);
        postRepository.save(PostForm.convert(postForm));

    }
}
