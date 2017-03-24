package com.example.spendingtracker2.Controllers;

import com.example.spendingtracker2.Models.Item;

/**
 * Created by carlcastello on 17/03/17.
 */

public class ItemController {
    private Item item;

    public ItemController(Item item) {
        this.item = item;
    }

    public String getItemName() {
        return this.item.getName();
    }

    public double getItemPrice() {
        return this.item.getPrice();
    }

    public int getItemQuantity() {
        return this.item.getQuantity();
    }

    public int getItemId() {
        return this.item.getId();
    }
    /**
     * Save transaction function
     * Save the transaction to JSON file
     */
    public void save() {

    }

    /**
     * Delete transaction Function
     * Delete the transaction from the JSON file
     */
    public void delete() {

    }

    /**
     * Update transaction Function
     * Update the transaction from the JSON file
     */
    public void update() {

    }
}
