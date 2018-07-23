package com.test.mohammaddvi.snappfood.Model;

public class Comment {
    private String name, date, comment, feelingemoji;
    private String[] foods;

    public Comment(String name, String date, String comment, String feelingemoji, String[] foods) {
        this.name = name;
        this.date = date;
        this.comment = comment;
        this.feelingemoji = feelingemoji;
        this.foods = foods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFeelingemoji() {
        return feelingemoji;
    }

    public void setFeelingemoji(String feelingemoji) {
        this.feelingemoji = feelingemoji;
    }

    public String[] getFoods() {
        return foods;
    }

    public void setFoods(String[] foods) {
        this.foods = foods;
    }
}
