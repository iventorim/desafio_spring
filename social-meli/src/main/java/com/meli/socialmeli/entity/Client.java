package com.meli.socialmeli.entity;

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

    public List<Seller> getFollowing() {
        return following;
    }

    public void setFollowing(List<Seller> following) {
        this.following = following;
    }
}
