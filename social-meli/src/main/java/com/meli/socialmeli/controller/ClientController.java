package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.ClientDTO;
import com.meli.socialmeli.entity.Client;
import com.meli.socialmeli.repository.ClientRepository;
import com.meli.socialmeli.service.ClientService;
import com.meli.socialmeli.service.CreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/users/{UserID}/followed/list")
    @ResponseStatus(HttpStatus.OK)
    private ClientDTO whoAmIFollowing(@PathVariable Integer UserID, @RequestParam(required = false) String order) {
        ClientDTO clienteDTO = ClientDTO.converter(clientService.findById(UserID, order));
        return clienteDTO;
    }
}
