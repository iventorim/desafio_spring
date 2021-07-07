package com.meli.socialmeli.dto;

import com.meli.socialmeli.entity.Client;

public class ClientDTO {

    private Integer userId;
    private String username;

    public ClientDTO(Integer userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public static ClientDTO convert(Client client){
        return new ClientDTO(client.getUserId(), client.getUsername());
    }
}
