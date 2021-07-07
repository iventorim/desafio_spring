package com.meli.socialmeli.controller;

import com.meli.socialmeli.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class ClientController {

    final ClientService clientService;
    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    @ResponseStatus(HttpStatus.OK)
    public void addFollower(@PathVariable int userId, @PathVariable int userIdToFollow){
        clientService.addUserFollower(userId, userIdToFollow);
    }

}
