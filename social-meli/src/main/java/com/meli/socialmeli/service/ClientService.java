package com.meli.socialmeli.service;

import com.meli.socialmeli.entity.Client;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.repository.ClientRepository;
import com.meli.socialmeli.repository.SellerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;


@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final SellerRepository sellerRepository;

    public ClientService(ClientRepository clientRepository, SellerRepository sellerRepository) {
        this.clientRepository = clientRepository;
        this.sellerRepository = sellerRepository;
    }

    public void addUserFollower(int userId, int userIdToFollow){
        if(!sellerRepository.existsById(userIdToFollow) || !clientRepository.existsById(userId)){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Cliente ou vendedor não existe.");
        }else{
            Seller seller = sellerRepository.getById(userIdToFollow);
            Client client = clientRepository.getById(userId);
            boolean alreadyExists = seller.getFollowers().stream().anyMatch(c -> c == client);
            if(alreadyExists){
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Registro já existe.");
            }else{
                seller.addFollower(client);
                sellerRepository.save(seller);
            }
        }
    }

    public void removeUserFollower(int userId, int userIdToUnfollow){
        if(!sellerRepository.existsById(userIdToUnfollow) || !clientRepository.existsById(userId)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Cliente ou vendedor não existe.");
        }else{
            Seller seller = sellerRepository.getById(userIdToUnfollow);
            Client client = clientRepository.getById(userId);
            boolean alreadyExists = seller.getFollowers().stream().anyMatch(c -> c == client);
            if(!alreadyExists){
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Registro não existe.");
            }else{
                seller.removeFollower(client);
                sellerRepository.save(seller);
            }

        }
    }
}
