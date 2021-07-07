package com.meli.socialmeli.service;

import com.meli.socialmeli.entity.Client;
import com.meli.socialmeli.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.repository.SellerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.util.NoSuchElementException;

@Service
public class ClientService {

    private final SellerRepository sellerRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, SellerRepository sellerRepository) {
        this.clientRepository = clientRepository;
        this.sellerRepository = sellerRepository;
    }

    public Client findById(Integer UserID, String order) {

        Client client = clientRepository.findById(UserID)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("UserId " + UserID + " não encontrado"));

        if (order != null) {
            client.getFollowing().sort((o1, o2) -> {
                if (order.equals("name_asc")) {
                    return o1.getUsername().compareTo(o2.getUsername());
                } else if (order.equals("name_desc")) {
                    return o2.getUsername().compareTo(o1.getUsername());
                }
                return 0;
            });
        }
        return client;
    }

    public void addUserFollower(int userId, int userIdToFollow) {
        if (!sellerRepository.existsById(userIdToFollow) || !clientRepository.existsById(userId)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Cliente ou vendedor não existe.");
        } else {
            Seller seller = sellerRepository.getById(userIdToFollow);
            Client client = clientRepository.getById(userId);
            boolean alreadyExists = seller.getFollowers().stream().anyMatch(c -> c == client);
            if (alreadyExists) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Registro já existe.");
            } else {
                seller.addFollower(client);
                sellerRepository.save(seller);
            }
        }
    }

    public void removeUserFollower(int userId, int userIdToUnfollow) {
        if (!sellerRepository.existsById(userIdToUnfollow) || !clientRepository.existsById(userId)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Cliente ou vendedor não existe.");
        } else {
            Seller seller = sellerRepository.getById(userIdToUnfollow);
            Client client = clientRepository.getById(userId);
            boolean alreadyExists = seller.getFollowers().stream().anyMatch(c -> c == client);
            if (!alreadyExists) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Registro não existe.");
            } else {
                seller.removeFollower(client);
                sellerRepository.save(seller);
            }
        }
    }
}