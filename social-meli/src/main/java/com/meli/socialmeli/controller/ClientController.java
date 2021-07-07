package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.ClientDTO;
import com.meli.socialmeli.entity.Client;
import com.meli.socialmeli.repository.ClientRepository;
import com.meli.socialmeli.service.ClientService;
import com.meli.socialmeli.service.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/users/{UserID}/followed/list")
    @ResponseStatus(HttpStatus.OK)
    private ClientDTO whoAmIFollowing(@PathVariable Integer UserID) {
        return ClientDTO.converter(clientService.findById(UserID));
    }

}
