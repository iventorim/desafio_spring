package com.meli.socialmeli.dto;

import com.meli.socialmeli.entity.Client;

import java.util.List;
import java.util.stream.Collectors;

public class ClientDTO {

    private String username;
    private Integer userId;
    private List<SellerDTO> followersDTO;

    // constructors

    public ClientDTO(String username, Integer userId, List<SellerDTO> followersDTO) {
        this.username = username;
        this.userId = userId;
        this.followersDTO = followersDTO;
    }

    public ClientDTO(Client client) {
        this.username = client.getUsername();
        this.userId = client.getUserId();
        this.followersDTO = SellerDTO.converter(client.getFollowing());
    }

    // DTO methods

    public static ClientDTO converter(Client client) {
        return new ClientDTO(client.getUsername(), client.getUserId(), SellerDTO.converter(client.getFollowing()));
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

    public List<SellerDTO> getSellers() {
        return followersDTO;
    }

    public void setSellers(List<SellerDTO> followersDTO) {
        this.followersDTO = followersDTO;
    }
}
