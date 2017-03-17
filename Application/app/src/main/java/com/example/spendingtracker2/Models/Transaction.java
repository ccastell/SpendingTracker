package com.example.spendingtracker2.Models;

import java.util.Calendar;

/**
 * A class of spending.
 * A Structure that a expenditure will be base on
 * @author carlcastello
 */

public class Transaction {

    private Calendar calendar;      // Date
    private Item item;        // Item
    private Store store;        // Location where the item is bought --later could be GeoPoints

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
    }

    /**
     * getDate function.
     * Returns the date when the item is purchase(recorded).
     * @return Calendar
     */
    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * getItem function.
     * Returns the Item.
     * @return String
     */
    public Item getItem() {
        return item;
    }

    /**
     * getLocation function.
     * Returns the Location where the item is purchase.
     * @return Store
     */
    public Store getLocation() {
        return store;
    }


}

