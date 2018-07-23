package com.test.mohammaddvi.snappfood.Model;

/**
 * This class is responsible to show the state of the order
 * and number of order
 */

public class OfferList {
    private boolean visibilityOrder;
    private int number;

    public OfferList(int number, boolean visibilityOrder) {
       this.number=number;
       this.visibilityOrder=visibilityOrder;
    }

    public boolean isVisibilityOrder() {
        return visibilityOrder;
    }

    public void setVisibilityOrder(boolean visibilityOrder) {
        this.visibilityOrder = visibilityOrder;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
