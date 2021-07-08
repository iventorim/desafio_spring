package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.FollowingListDTO;
import com.meli.socialmeli.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("{userId}/follow/{userIdToFollow}")
    @ResponseStatus(HttpStatus.OK)
    public void addFollower(@PathVariable int userId, @PathVariable int userIdToFollow) {
        clientService.addUserFollower(userId, userIdToFollow);
    }

    @PostMapping("{userId}/unfollow/{userIdToUnfollow}")
    @ResponseStatus(HttpStatus.OK)
    public void removeFollower(@PathVariable int userId, @PathVariable int userIdToUnfollow) {
        clientService.removeUserFollower(userId, userIdToUnfollow);
    }

    @GetMapping("{userId}/followed/list")
    @ResponseStatus(HttpStatus.OK)
    private FollowingListDTO whoAmIFollowing(@PathVariable int userId, @RequestParam(required = false) String order) {
        return FollowingListDTO.converter(clientService.getUserFollowingSellers(userId, order));
    }
}
