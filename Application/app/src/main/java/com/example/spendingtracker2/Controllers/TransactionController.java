package com.example.spendingtracker2.Controllers;

import android.content.Context;

import com.example.spendingtracker2.Models.Transaction;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by carlcastello on 17/03/17.
 */
public class TransactionController {
    private Context context;
    private Transaction transaction;

    public TransactionController(Transaction transaction) {
        this.transaction = transaction;
    }

    public TransactionController(Context context, Transaction transaction) {
        this.context = context;
        this.transaction = transaction;
    }

    public int getTransactionId() {
        return this.transaction.getId();
    }

    /**
     * Get transaction Date Function
     * Returns a string with the transaction date
     * @return String
     */
    public String getTransactionCalendar() {
        Calendar calendar = transaction.getCalendar();
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        String formattedDate = format.format(calendar.getTime());
        return formattedDate;
    }

    public String getTransactionDate() {
        Calendar calendar = transaction.getCalendar();
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy");
        String formattedTime = format.format(calendar.getTime());
        return formattedTime;
    }

    /**
     * Get transaction Time Function
     * Returns a string with the transaction time
     * @return String
     */
    public String getTransactionTime() {
        Calendar calendar = transaction.getCalendar();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        String formattedTime = format.format(calendar.getTime());
        return formattedTime;
    }

    /**
     * Save transaction function
     * Save the transaction to JSON file
     */
    public void save() {
        AddJsonController.AddTransactionTask addTransactionTask = new AddJsonController.AddTransactionTask(this.context);
        addTransactionTask.execute(this.transaction);
    }

    /**
     * Update transaction Function
     * Update the transaction from the JSON file
     */
    public void update() {

    }

}
