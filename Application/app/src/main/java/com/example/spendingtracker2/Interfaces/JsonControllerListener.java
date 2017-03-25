package com.example.spendingtracker2.Interfaces;

import com.example.spendingtracker2.Models.Transaction;

import java.util.ArrayList;

/**
 * Created by carlcastello on 24/03/17.
 */

public interface JsonControllerListener {
    public abstract void updateNavigationItems(ArrayList<Transaction> transactions);
}
