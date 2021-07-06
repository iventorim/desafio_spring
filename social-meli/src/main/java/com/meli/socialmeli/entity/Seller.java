package com.meli.socialmeli.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Seller extends User {

    @ManyToMany
    @JoinTable(
            name = "sellers_followers",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private List<Client> followers;

}
