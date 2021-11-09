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

    public NumberDonate() {
    }

    public NumberDonate(int numberOfDonate) {
        this.numberOfDonate = numberOfDonate;
    }

    public int getNumberOfDonate() {
        return numberOfDonate;
    }

    public void setNumberOfDonate(int numberOfDonate) {
        this.numberOfDonate = numberOfDonate;
    }


}
