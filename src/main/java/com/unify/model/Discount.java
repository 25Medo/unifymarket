package com.unify.model;

/**
 * Discount on items, can be price based like 10%
 * Can also be quantity based on:
 * the same item: Buy 2 Soup get the 3rd at 50%
 * another item: Buy 2 Soup get another item at half price
 */
public class Discount {
    private String itemName;
    private double value;
    private int quantityRequired; //O means the discount is by default available on price, 1 means if you buy 1 the discount is for the next one, on quantity

    public Discount() {
    }

    /**
     *
     * @param itemName on which the discount is applied
     * @param value of the discount
     * @param quantityRequired O means on price, 1 means buy 1 the discount is for the next one, on quantity
     */
    public Discount(String itemName, double value, int quantityRequired) {
        this.itemName = itemName;
        this.value = value;
        this.quantityRequired = quantityRequired;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setQuantityRequired(int quantityRequired) {
        this.quantityRequired = quantityRequired;
    }

    public String getItemName() {
        return itemName;
    }

    public double getValue() {
        return value;
    }

    public int getQuantityRequired() {
        return quantityRequired;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "itemName='" + itemName + '\'' +
                ", value=" + value +
                ", quantityRequired=" + quantityRequired +
                '}';
    }
}
