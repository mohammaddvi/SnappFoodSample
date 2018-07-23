package com.test.mohammaddvi.snappfood.Model;

public class Restaurant {
    private String name, place, foodCat;
    private String deliverPrice;
    private String imageView;

    public Restaurant(String name, String place, String foodCat, String deliverPrice,String imageView) {
        this.name = name;
        this.place = place;
        this.foodCat = foodCat;
        this.deliverPrice = deliverPrice;
        this.imageView = imageView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getFoodCat() {
        return foodCat;
    }

    public void setFoodCat(String foodCat) {
        this.foodCat = foodCat;
    }

    public String getDeliverPrice() {
        return deliverPrice;
    }

    public void setDeliverPrice(String deliverPrice) {
        this.deliverPrice = deliverPrice;
    }

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }
}
