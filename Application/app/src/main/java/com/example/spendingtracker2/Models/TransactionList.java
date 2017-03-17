package com.example.spendingtracker2.Models;

import java.util.ArrayList;
import java.util.Date;

/**
 * TransactionList class
 * A class that stores transactions.
 * @author carlcastello
 */
public class TransactionList {

    private String title; // Title of the Transaction list -- will be done automatically
    private Date date;    // Date when the transaction list is initialized
    private ArrayList<Transaction> transactionsList; // List of transactions

    /**
     * class consytuctor that initialize an empty transactionList
     * @param date
     * @param title
     */
    public TransactionList(Date date, String title) {
        this.transactionsList = new ArrayList<>();
        this.date = date;
        this.title = title;
    }

    /**
     * class constructor with an arrayList parameter to cast
     * arrayList of Transaction into an TransactionList
     * @param date
     * @param title
     * @param transactionsList
     */
    public TransactionList(Date date, String title, ArrayList<Transaction> transactionsList) {
        this.transactionsList = transactionsList;
        this.date = date;
        this.title = title;
    }

    /**
     * getTransactionsList
     * returns a collections of transactions
     * @return transactionsList
     */
    public ArrayList<Transaction> getTransactionsList() {
        return transactionsList;
    }

    /**
     * addTransactionList
     * adds a transaction in the beginning of the list
     * @param transaction
     */
    public void addTransaction(Transaction transaction) {
        transactionsList.add(0,transaction);
    }

    /**
     * getSpecificTransaction
     * An integer parameter to return a Transaction on the integer's position
     * @param index
     * @return Transaction
     */
    public Transaction getSpecificTransaction(int index) {
        return transactionsList.get(index);
    }

    /**
     * getTitle
     * for UI purpose
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * getDate
     * returns the date when this transactionList is initialize.
     * @return date
     */
    public Date getDate() {
        return date;
    }
}
