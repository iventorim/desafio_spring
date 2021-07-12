package com.meli.socialmeli.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("product_id")
    private Integer productId;

    @NotBlank
    private String productName;

    @NotBlank
    private String type;

    @NotBlank
    private String brand;

    @NotBlank
    private String color;

    @NotBlank
    private String notes;

    public Product() {}

    public Product(String productName, String type, String brand, String color, String notes) {
        this.productName = productName;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.notes = notes;
    }

    public void setProductId(Integer id) {
        this.productId = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
