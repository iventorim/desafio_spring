package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.ClientDTO;
import com.meli.socialmeli.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    @ResponseStatus(HttpStatus.OK)
    public void addFollower(@PathVariable int userId, @PathVariable int userIdToFollow){
        clientService.addUserFollower(userId, userIdToFollow);
    }

    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    @ResponseStatus(HttpStatus.OK)
    public void removeFollower(@PathVariable int userId, @PathVariable int userIdToUnfollow){
        clientService.removeUserFollower(userId, userIdToUnfollow);
    }

    @GetMapping("/users/{UserID}/followed/list")
    @ResponseStatus(HttpStatus.OK)
    private ClientDTO whoAmIFollowing(@PathVariable Integer UserID, @RequestParam(required = false) String order) {
        return ClientDTO.converter(clientService.findById(UserID, order));
    }
}
