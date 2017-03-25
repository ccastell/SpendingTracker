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
    public  static class AddTransactionTask extends AsyncTask<Transaction, Void, JSONObject> {
        final String FILENAME = "/data.sav";
        private Context ContextAsync;

        public AddTransactionTask(Context context){
            this.ContextAsync = context;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        /**
         * Adds transaction to Json Asynchronously
         * @param transactions
         * @return Null
         */
        @Override
        protected JSONObject doInBackground(Transaction... transactions) {

            Transaction transaction = transactions[0];
            TransactionController tc;
            StoreController st;
            ItemController it;

            JSONObject transactionJson = null;

            tc = new TransactionController(transaction);
            st = new StoreController(transaction.getStore());
            it = new ItemController(transaction.getItem());

            try {
                JSONObject itemJson = new JSONObject();
                itemJson.put("ID", it.getItemId());
                itemJson.put("Name", it.getItemName());
                itemJson.put("Price", it.getItemPrice());
                itemJson.put("Quantity", it.getItemQuantity());

                JSONObject storeJson = new JSONObject();
                storeJson.put("ID", st.getStoreId());
                storeJson.put("Name", st.getStoreName());
//          storeJson.put("Longitude",st.getLongitude());
//          storeJson.put("Latitude",st.getLatitude());

                transactionJson = new JSONObject();
                transactionJson.put("ID", tc.getTransactionId());
                transactionJson.put("Store", storeJson);
                transactionJson.put("Item", itemJson);
                transactionJson.put("Date", tc.getTransactionCalendar());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return transactionJson;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            System.out.println(json.toString());
            try {
                FileWriter file = new FileWriter(ContextAsync.getFilesDir()+FILENAME);
                BufferedWriter output = new BufferedWriter(file);
                output.write(json.toString());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
