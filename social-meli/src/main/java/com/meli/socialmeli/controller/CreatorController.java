package com.meli.socialmeli.controller;


import com.meli.socialmeli.service.CreatorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatorController {

    final CreatorService creatorService;

    @Autowired
    public CreatorController(CreatorService creatorService) {
        this.creatorService = creatorService;
    }

    @ApiOperation(value = "Funcionalidade para popular o banco de dados")
    @GetMapping("create")
    public void createDB() {
        creatorService.populateDatabase();
    }

}
