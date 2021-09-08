package com.cognixia.restaurant.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Restaurant implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer restaurantId;

    // @Column -> provides definitions for how columns should be set up
    //         -> name = how column name will be set when table created
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    // unique = true --> unique constraint
    // nullable = false --> not null constraints
    @Column( name = "cuisine" )
    private String cuisine;

    @Column( name = "description")
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
