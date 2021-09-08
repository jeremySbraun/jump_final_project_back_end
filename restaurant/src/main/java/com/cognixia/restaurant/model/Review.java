package com.cognixia.restaurant.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Review implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @Column(name = "body")
    private String body;

    @Column(name = "time") //format: "8:45:23" (hh:mm:ss)
    private Time time;

    @Column(name = "date") //format: "2021-12-25" (yyyy-mm-dd)
    private java.sql.Date date;

    @Column( name = "rating" ) 
    private Integer rating;

    @Column( name = "restaurantId")
    private Integer restaurantId;

    @Column( name = "userId")
    private Integer userId;


    public Review() {
        super();
    }

    public Review(String body, Time time, Date date, Integer rating, Integer restaurantId, Integer userId) {
        this.body = body;
        this.time = time;
        this.date = date;
        this.rating = rating;
        this.restaurantId = restaurantId;
        this.userId = userId;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
