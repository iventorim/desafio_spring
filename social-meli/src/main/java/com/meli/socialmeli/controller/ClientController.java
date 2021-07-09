package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.FollowingListDTO;
import com.meli.socialmeli.service.ClientService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Funcionalidade para um cliente começar a seguir um determinado vendedor")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "Id do cliente", example = "1"),
            @ApiImplicitParam(name = "userId", value = "Id do vendedor que se deseja seguir", example = "7")
    })
    @PostMapping("{userId}/follow/{userIdToFollow}")
    @ResponseStatus(HttpStatus.OK)
    public void addFollower(@PathVariable int userId, @PathVariable int userIdToFollow) {
        clientService.addUserFollower(userId, userIdToFollow);
    }

    @ApiOperation(value = "Funcionalidade para um cliente parar de seguir um determinado vendedor")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "Id do cliente", example = "1"),
            @ApiImplicitParam(name = "userId", value = "Id do vendedor que se deseja parar de seguir", example = "7")
    })
    @PostMapping("{userId}/unfollow/{userIdToUnfollow}")
    @ResponseStatus(HttpStatus.OK)
    public void removeFollower(@PathVariable int userId, @PathVariable int userIdToUnfollow) {
        clientService.removeUserFollower(userId, userIdToUnfollow);
    }

    @ApiOperation(value = "Funcionalidade para buscar a lista de vendedores que um cliente está seguindo")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "Id do cliente que se deseja realizar a busca", example = "1"),
    })
    @GetMapping("{userId}/followed/list")
    @ResponseStatus(HttpStatus.OK)
    private FollowingListDTO whoAmIFollowing(@PathVariable int userId, @RequestParam(required = false) String order) {
        return FollowingListDTO.converter(clientService.getUserFollowingSellers(userId, order));
    }
}
