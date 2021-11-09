package com.dammak.project401.models;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class NumberDonate {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numberOfDonate;
    private int numberOfUser;
    private String username;

    public NumberDonate() {
    }

    public NumberDonate(int numberOfDonate, String username,int numberOfUser) {
        this.numberOfDonate = numberOfDonate;
        this.username = username;
        this.numberOfUser=numberOfUser;
    }

    public int getNumberOfDonate() {
        return numberOfDonate;
    }

    public void setNumberOfDonate(int numberOfDonate) {
        this.numberOfDonate = numberOfDonate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumberOfUser() {
        return numberOfUser;
    }

    public void setNumberOfUser(int numberOfUser) {
        this.numberOfUser = numberOfUser;
    }
}
