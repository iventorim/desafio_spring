package com.meli.socialmeli.controller;

import com.meli.socialmeli.dto.FollowersCountDTO;
import com.meli.socialmeli.entity.Seller;
import com.meli.socialmeli.service.SellerService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.meli.socialmeli.dto.FollowersListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


@RestController
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @ApiOperation(value = "Funcionalidade para buscar o número de clientes que seguem um determinado vendedor")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "Id do vendedor que se deseja realizar a busca", example = "6"),
    })
    @GetMapping("/users/{userId}/followers/count")
    @ResponseStatus(value = HttpStatus.OK)
    public FollowersCountDTO getFollowersSellerCount(@PathVariable int userId) {
        return sellerService.getFollowersSellerCount(userId);
    }

    @ApiOperation(value = "Funcionalidade para buscar a lista de todos os clientes que seguem determinado vendedor")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "Id do vendedor que se deseja realizar a busca", example = "6"),
            @ApiImplicitParam(name = "order", value = "Tipo de ordenação: name_asc, name_desc", example = "name_asc")
    })
    @GetMapping("/users/{userId}/followers/list")
    public FollowersListDTO followersList(@PathVariable int userId,  @RequestParam(required = false) String order){
        return sellerService.getFollowers(userId,order);
    }

    @ApiOperation(value = "Funcionalidade para cadastrar um novo vendedor")
    @PostMapping("/users/newseller")
    @ResponseStatus(HttpStatus.OK)
    public void newSeller(@RequestBody Seller seller){
        sellerService.addSeller(seller);
    }

    @ApiOperation(value = "Funcionalidade para atualizar vendedor")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "Id do vendedor que se deseja atualizar", example = "6"),
    })
    @PutMapping("/users/{userId}/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateSeller(@PathVariable int userId, @RequestBody Seller seller){
        sellerService.updateSeller(userId, seller);
    }

    @ApiOperation(value = "Funcionalidade para deletar um vendedor")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "Id do vendedor que se deseja excluir", example = "6"),
    })
    @DeleteMapping("/users/{userId}/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSeller(@PathVariable int userId){
        sellerService.deleteSeller(userId);
    }

    @ApiOperation(value = "Funcionalidade para listar todos vendedores cadastrados")
    @GetMapping("/users/sellers")
    @ResponseStatus(HttpStatus.OK)
    public List<Seller> getSellers(){
        return sellerService.getSellers();
    }
    
}
