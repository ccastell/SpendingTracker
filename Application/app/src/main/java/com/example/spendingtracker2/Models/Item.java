package com.example.spendingtracker2.Models;

/**
 * Item Class
 * A description on the Items that are purchased during a transaction
 * Created by carlcastello on 16/03/17.
 */

public class Item {
    private String name;         // Item Name
    private Double price;         // Price of the item
    private int quantity;        // Quantity

    /**
     * Class Initializer
     * @param name
     * @param price
     * @param quantity
     */
    public Item(String name, Double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Get Item Function
     * returns item Name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get item price Function
     * returns item Price
     * @return Double
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Get item quantity Function
     * returns item quantity
     * @return Int
     */
    public int getQuantity() {
        return quantity;
    }
}
