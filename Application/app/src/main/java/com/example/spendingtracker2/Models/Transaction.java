package com.example.spendingtracker2.Models;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A class of spending.
 * A Structure that a expenditure will be base on
 * @author carlcastello
 */

public class Transaction {

    private int id;                 // Transaction ID
    private Calendar calendar;      // Date
    private Item item;        // Item
    private Store store;        // Location where the item is bought --later could be GeoPoints
    static AtomicInteger nextID = new AtomicInteger();

    /**
     * A class constructor that initialize variables important to the class
     * @param item
     * @param store
     * @param calendar
     */
    public Transaction(Item item, Store store, Calendar calendar) {
        this.store = store;
        this.item = item;
        this.calendar = calendar;
        this.id = nextID.incrementAndGet();
    }

    /**
     * Transaction from json file
     * @param id
     * @param item
     * @param store
     * @param calendar
     */
    public Transaction(int id, Item item, Store store, Calendar calendar) {
        this.store = store;
        this.item = item;
        this.calendar = calendar;
        this.id = id;
    }

    /**
     * getDate function.
     * Returns the date when the item is purchase(recorded).
     * @return Calendar
     */
    public Calendar getCalendar() {
        return this.calendar;
    }

    /**
     * getItem function.
     * Returns the Item.
     * @return String
     */
    public Item getItem() {
        return this.item;
    }

    /**
     * getLocation function.
     * Returns the Location where the item is purchase.
     * @return Store
     */
    public Store getStore() {
        return this.store;
    }

    /**
     * getId function
     * Reutnrs the id of the transaction
     * @return int
     */
    public int getId() {
        return this.id;
    }
}

