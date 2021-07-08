package com.meli.socialmeli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Client extends User {

    public Client(String username) {
        super(username);
    }

    @ManyToMany(mappedBy = "followers")
    private List<Seller> following;

    public Client() {

    }

    @JsonIgnore
    public List<Seller> getFollowing() {
        return following;
    }

    public void setFollowing(List<Seller> following) {
        this.following = following;
    }
}
