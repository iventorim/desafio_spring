package com.meli.socialmeli.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPost;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;

    @ManyToOne()
    @JoinColumn(name="productId")
    private Product detail;

    @NotBlank
    private Integer category;

    @NotBlank
    private Double price;

    private boolean hasPromo;

    private Double discount;

    @ManyToOne(optional = false)
    @JoinColumn(name="seller_id")
    private Seller seller;

    public Post(LocalDate date, Product detail, Integer category, Double price, Seller seller) {
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.seller = seller;
        this.hasPromo = false;
        this.discount = 0.0;
    }

    public Post(LocalDate date, Product detail, Integer category, Double price, boolean hasPromo, Double discount, Seller seller) {
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
        this.hasPromo = hasPromo;
        this.discount = discount;
        this.seller = seller;
    }

    public Post() {}

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Product getDetail() {
        return detail;
    }

    public void setDetail(Product detail) {
        this.detail = detail;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public void setIdPost(Integer id) {
        this.idPost = id;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

}
