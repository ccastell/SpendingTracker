package com.example.spendingtracker2.Interfaces;

/**
 * Created by carlcastello on 16/03/17.
 * http://stackoverflow.com/questions/15121373/returning-string-from-dialog-fragment-back-to-activity
 */

public interface OnCompleteListener {
    public abstract void onComplete(String item_name, String store_name, Double cost, int quantity);
}
