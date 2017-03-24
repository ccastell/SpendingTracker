package com.example.spendingtracker2.Controllers;

/**
 * Created by carlcastello on 16/03/17.
 */

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;

import com.example.spendingtracker2.Models.Item;
import com.example.spendingtracker2.Models.Transaction;
import com.example.spendingtracker2.Models.TransactionList;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Controller for Adding transaction to Json
 */
public class AddJsonController {
    public  static class AddTransactionTask extends AsyncTask<Transaction, Void, Void> {
        final String FILENAME = "/data.sav";
        private Context ContextAsync;

        public AddTransactionTask(Context context){
            this.ContextAsync = context;
        }

        /**
         * Adds transaction to Json Asynchronously
         * @param transactions
         * @return Null
         */
        @Override
        protected Void doInBackground(Transaction... transactions) {

            TransactionController tc;
            StoreController st;
            ItemController it;


            for (Transaction transaction : transactions) {
                tc = new TransactionController(transaction);
                st = new StoreController(transaction.getStore());
                it = new ItemController(transaction.getItem());

                try {
                    JSONObject itemJson = new JSONObject();
                    itemJson.put("ID",it.getItemId());
                    itemJson.put("Name",it.getItemName());
                    itemJson.put("Price",it.getItemPrice());
                    itemJson.put("Quantity",it.getItemQuantity());

                    JSONObject storeJson = new JSONObject();
                    storeJson.put("ID",st.getStoreId());
                    storeJson.put("Name",st.getStoreName());
//                    storeJson.put("Longitude",st.getLongitude());
//                    storeJson.put("Latitude",st.getLatitude());

                    JSONObject transactionJson = new JSONObject();
                    transactionJson.put("ID",tc.getTransactionId());
                    transactionJson.put("Store",storeJson);
                    transactionJson.put("Item",itemJson);
                    transactionJson.put("Time",tc.getTransactionTime());
                    transactionJson.put("Date",tc.getTransactionDate());


//                    FileWriter file = new FileWriter(ContextAsync.getFilesDir()+FILENAME);
//                    BufferedWriter output = new BufferedWriter(file);
//                    output.write(transactionJson.toString());

                    System.out.println("\nJSON Object: " + transactionJson);

//                } catch (FileNotFoundException e) {
//                    throw new RuntimeException(e);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
