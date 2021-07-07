package com.meli.socialmeli.service;

import com.meli.socialmeli.entity.Client;
import com.meli.socialmeli.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findById(Integer UserID) {
        return clientRepository
                .findById(UserID)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("UserId " +UserID + " não encontrado"));
    }

}
