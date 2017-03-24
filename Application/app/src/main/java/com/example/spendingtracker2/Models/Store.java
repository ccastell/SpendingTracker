package com.example.spendingtracker2.Models;

import android.location.Location;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by carlcastello on 16/03/17.
 */

public class Store {

    private int id;
    private String name;       // Name of the store
    private Location location;
    static AtomicInteger nextID = new AtomicInteger();

    /**
     * Class variable initializer
     * @param name
     * @param location
     */
    public Store(String name, Location location) {
        this.name = name;
        this.location = location;
        this.id = nextID.incrementAndGet();
    }

    /**
     * Get store name function
     * Returns the store name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get location function
     * Returns the store location
     * @return Location
     */
    public Location getLocation() {
        return location;
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
