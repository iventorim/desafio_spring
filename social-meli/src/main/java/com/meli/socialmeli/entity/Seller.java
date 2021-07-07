package com.meli.socialmeli.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller extends User {

    @ManyToMany
    @JoinTable(
            name = "sellers_followers",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private List<Client> followers;

    public Seller(String username, List<Client> followers) {
        super(username);
        this.followers = followers;
    }

    @OneToMany(targetEntity = Post.class, mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    public Seller() {}

    public List<Client> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Client> followers) {
        this.followers = followers;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
