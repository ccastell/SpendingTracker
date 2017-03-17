package com.example.spendingtracker2.Models;

import android.location.Location;

import java.util.Calendar;

/**
 * Created by carlcastello on 16/03/17.
 */

public class Store {

    private String name;       // Name of the store
    private Location location;

    /**
     * Class variable initializer
     * @param name
     * @param location
     */
    public Store(String name, Location location) {
        this.name = name;
        this.location = location;
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

}
