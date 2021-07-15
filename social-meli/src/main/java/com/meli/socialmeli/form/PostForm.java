package com.meli.socialmeli.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meli.socialmeli.entity.Post;
import com.meli.socialmeli.entity.Product;
import com.meli.socialmeli.entity.Seller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class PostForm {

    @NotNull
    private Integer userId;
    private Integer id_post;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    @NotNull
    private Product detail;
    @NotNull
    private Integer category;
    @NotNull
    private Double price;
    private Seller seller;
    private boolean hasPromo = false;
    private double discount = 0.00;

    public PostForm() {
    }

    public PostForm(Integer userId, Integer id_post, LocalDate date, Product detail, Integer category, Double price, Seller seller) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.seller = seller;
    }

    public PostForm(Integer userId, Integer id_post, LocalDate date, Product detail, Integer category, Double price,
                    Seller seller, boolean hasPromo, double discount) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.seller = seller;
        this.hasPromo = hasPromo;
        this.discount = discount;
    }

    // Getter Methods

    public Integer getUserId() {
        return userId;
    }

    public Integer getId_post() {
        return id_post;
    }

    public LocalDate getDate() {
        return date;
    }

    public Product getDetail() {
        return detail;
    }

    public Integer getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDetail(Product detail) {
        this.detail = detail;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public boolean isHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public static Post convert(PostForm postForm) {
        return new Post(postForm.date,
                postForm.getDetail(),
                postForm.getCategory(),
                postForm.getPrice(),
                postForm.isHasPromo(),
                postForm.getDiscount(),
                postForm.getSeller()
        );
    }
}
