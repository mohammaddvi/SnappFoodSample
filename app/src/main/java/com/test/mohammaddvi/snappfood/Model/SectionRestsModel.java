package com.test.mohammaddvi.snappfood.Model;

import java.util.ArrayList;

public class SectionRestsModel {
    private String headerTitle;
    private ArrayList<Restaurant> someRestInSection;

    public SectionRestsModel() {
    }

    public SectionRestsModel(String headerTitle, ArrayList<Restaurant> someRestInSection) {
        this.headerTitle = headerTitle;
        this.someRestInSection = someRestInSection;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<Restaurant> getSomeRestInSection() {
        return someRestInSection;
    }

    public void setSomeRestInSection(ArrayList<Restaurant> someRestInSection) {
        this.someRestInSection = someRestInSection;
    }
}
