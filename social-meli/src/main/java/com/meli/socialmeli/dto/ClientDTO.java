package com.meli.socialmeli.dto;

import com.meli.socialmeli.entity.Client;
import com.meli.socialmeli.entity.Seller;

import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO {

    private String username;
    private Integer userId;
    private List<Seller> followed;

    // constructors

    public ClientDTO(String username, Integer userId, List<Seller> followed) {
        this.username = username;
        this.userId = userId;
        this.followed = followed;
    }

    public ClientDTO(Client client) {
        this.username = client.getUsername();
        this.userId = client.getUserId();
        this.followed = client.getFollowing();
    }

    // DTO methods

    public static ClientDTO converter(Client client) {
        return new ClientDTO(client.getUsername(), client.getUserId(), client.getFollowing());
    }

    public static List<ClientDTO> converter(List<Client> clients) {
        return clients.stream()
                .map(c -> ClientDTO.converter(c))
                .collect(Collectors.toList());
    }

    // getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Seller> getFollowed() {
        return followed;
    }

    public void setFollowed(List<Seller> followed) {
        this.followed = followed;
    }
}
