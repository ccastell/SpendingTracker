package com.example.spendingtracker2.Models;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Item Class
 * A description on the Items that are purchased during a transaction
 * Created by carlcastello on 16/03/17.
 */

public class Item {

    private int id;
    private String name;         // Item Name
    private Double price;         // Price of the item
    private int quantity;        // Quantity
    static AtomicInteger nextID = new AtomicInteger();

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
        this.id = nextID.incrementAndGet();
    }

    public Item(int id, String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.id = id;
    }

    /**
     * Get Item Function
     * returns item Name
     * @return String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get item price Function
     * returns item Price
     * @return Double
     */
    public Double getPrice() {
        return this.price;
    }

    /**
     * Get item quantity Function
     * returns item quantity
     * @return Int
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Get id Function
     * Returns the store ID
     * @return int
     */
    public int getId() {
        return this.id;
    }
}
