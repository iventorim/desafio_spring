package com.meli.socialmeli.controller;


import com.meli.socialmeli.dto.CountPromoSellerDTO;
import com.meli.socialmeli.dto.ListPromoProdSellerDTO;
import com.meli.socialmeli.entity.Product;
import com.meli.socialmeli.service.ProductService;
import com.meli.socialmeli.service.SellerService;
import com.meli.socialmeli.dto.UserFollowingPostsDTO;
import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.service.ClientService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final SellerService sellerService;
    private final ClientService clientService;
    private final ProductService productService;

    @Autowired
    public ProductController(SellerService sellerService, ClientService clientService, ProductService productService) {
        this.sellerService = sellerService;
        this.clientService = clientService;
        this.productService = productService;
    }

    @ApiOperation(value = "Funcionalidade para buscar a quantidade de produtos promocionais de um vendedor")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "Id do vendedor que se deseja realizar a busca", example = "6"),
    })
    @GetMapping("{userId}/countPromo")
    @ResponseStatus(HttpStatus.OK)
    public CountPromoSellerDTO getCountPromoSeller(@PathVariable int userId) {
        return sellerService.getCountPostPromoSeller(userId);
    }

    @ApiOperation(value = "Funcionalidade para buscar a lista de produtos promocionais de um vendedor")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "Id do vendedor que se deseja realizar a busca", example = "6"),
            @ApiImplicitParam(name="order", value = "Tipo de ordenação desejada: name_asc, name_desc")
    })
    @GetMapping("{userId}/list")
    @ResponseStatus(HttpStatus.OK)
    public ListPromoProdSellerDTO getListPromoProdSeller(@PathVariable int userId, @RequestParam(required = false) String order) {
        return sellerService.getListPromoProdSeller(userId, order);
    }

    @ApiOperation(value = "Funcionalidade para buscar a lista de publicações das últimas duas semanas feitas pelos vendedores que um cliente segue")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "userId", value = "Id do cliente que se deseja realizar a busca", example = "1"),
            @ApiImplicitParam(name="order", value = "Tipo de ordenação desejada: date_asc, date_desc")
    })
    @GetMapping("followed/{userId}/list")
    @ResponseStatus(HttpStatus.OK)
    public UserFollowingPostsDTO listSellersPostsByUserId(@PathVariable int userId, @RequestParam(required = false) String order) {
        List<Post> posts = clientService.getUserFollowingSellersPosts(userId, order);

        return new UserFollowingPostsDTO(userId, posts);
    }

    @ApiOperation(value = "Funcionalidade para cadastrar um produto")
    @PostMapping("/newProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createNewProduct(@RequestBody Product product) {
        return productService.createNewProduct(product);
    }

    @ApiOperation(value = "Funcionalidade para buscar todos os produtos cadastrados")
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @ApiOperation(value = "Funcionalidade para atualizar um produto cadastrado")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "Id do produto que se deseja atualizar", example = "1"),
    })
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable int id, @RequestBody Product product){
        productService.updateProduct(id, product);
    }

    @ApiOperation(value = "Funcionalidade para excluir um produto cadastrado")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "Id do produto que se deseja excluir", example = "1"),
    })
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
    }
}
