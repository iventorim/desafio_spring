package com.meli.socialmeli.service;

import com.meli.socialmeli.entity.Client;
import com.meli.socialmeli.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
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
                } else if(order.equals("name_desc")){
                    return o2.getUsername().compareTo(o1.getUsername());
                }
                return 0;
            });
        }
        return client;
    }
}

