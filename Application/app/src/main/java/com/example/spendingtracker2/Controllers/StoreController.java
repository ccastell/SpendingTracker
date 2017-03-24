package com.example.spendingtracker2.Controllers;

import android.location.Location;

import com.example.spendingtracker2.Models.Store;

/**
 * Created by carlcastello on 17/03/17.
 */

public class StoreController {
    private Store store;

    public StoreController(Store store) {
        this.store = store;
    }

    /**
     * get Storename Function
     * Returns the name of the store
     * @return String
     */
    public String getStoreName() {
        return this.store.getName();
    }

    /**
     * Get Latitude function
     * Returns the Latitude of the location
     * @return String
     */
    public double getLatitude() {
        Location location = this.store.getLocation();
        double latitude = location.getLatitude();
        return latitude;
    }

    /**
     * Get longitude function
     * Returns the longitude of the location
     * @return String
     */
    public double getLongitude() {
        Location location = this.store.getLocation();
        double latitude = location.getLongitude();
        return latitude;
    }

    /**
     * Get Id function
     * Returns the store ID
     * @return String
     */
    public int getStoreId() {
        return this.store.getId();
    }

    /**
     * Save Store function
     * Save the store to JSON file
     */
    public void save() {

    }

    /**
     * Delete Store Function
     * Delete the store from the JSON file
     */
    public void delete() {

    }

    /**
     * Update Store Function
     * Update the Store from the JSON file
     */
    public void update() {

    }
}
