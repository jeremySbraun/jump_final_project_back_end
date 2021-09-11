package com.cognixia.jump.springcloud.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

public class Restaurant implements Serializable{
    
    private static final long serialVersionUID = 1L;


    private Integer restaurantId;

    private String name;

    private String address;

    private String cuisine;

    private String description;

    @Transient
    List<Review> reviews;

    public Restaurant(){
        super();
    }

    public Restaurant(String name, String address, String cuisine, String description, List<Review> reviews) {
        this.name = name;
        this.address = address;
        this.cuisine = cuisine;
        this.description = description;
        this.reviews = reviews;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    
    

}

