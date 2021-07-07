package com.meli.socialmeli.service;

import com.meli.socialmeli.entity.Client;
import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.exception.ClientNotFoundException;
import com.meli.socialmeli.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Post> getFollowingPosts(int userId) {
        Client client = repository.findById(userId)
                .orElseThrow(() -> new ClientNotFoundException("Cliente " + userId + " não encontrado."));

        List<Seller> following = client.getFollowing();
        List<Post> postList = new ArrayList<>();

        following
                .stream()
                .map(Seller::getPosts)
                .forEach(postList::addAll);

        return postList;
    }
}
