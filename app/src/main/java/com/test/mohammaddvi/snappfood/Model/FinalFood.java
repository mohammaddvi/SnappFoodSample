package com.test.mohammaddvi.snappfood.Model;

import android.content.Intent;

import java.io.Serializable;

/**
 * Created by Asus on 18/07/2018.
 */

public class FinalFood implements Serializable {
    private String name, price, details;
    private Integer ordernumber;

    public FinalFood(String name, String price, String details, Integer ordernumber) {
        this.name = name;
        this.price = price;
        this.details = details;
        this.ordernumber = ordernumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(Integer ordernumber) {
        this.ordernumber = ordernumber;
    }
}
