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

    @OneToMany
    @JoinColumn(name = "seller_id")
    private List<Post> posts;

    public Seller(String username, List<Client> followers) {
        super(username);
        this.followers = followers;
    }

    public Seller() {

    }

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
