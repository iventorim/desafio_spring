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

    public Client findById(Integer UserID) {

        //        if(UserID instanceof Integer == false){
        //            throw new MethodArgumentTypeMismatchException(UserID,Number,"UserID invalido");
        //        }
        return clientRepository
                .findById(UserID)
                .stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("UserId " + UserID + " não encontrado"));
    }

}
