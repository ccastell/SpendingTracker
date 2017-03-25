package com.example.spendingtracker2.Controllers;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;

import com.example.spendingtracker2.Interfaces.JsonControllerListener;
import com.example.spendingtracker2.Models.Item;
import com.example.spendingtracker2.Models.Store;
import com.example.spendingtracker2.Models.Transaction;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by carlcastello on 24/03/17.
 */

public class GetJsonController {

    public  static class GetRecentTransactionTask extends AsyncTask<Transaction, Void, ArrayList<Transaction> > {
        final String FILENAME = "/data.sav";
        private Context ContextAsync;

        public GetRecentTransactionTask(Context context){
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
        protected ArrayList<Transaction> doInBackground(Transaction... transactions) {
            ArrayList<Transaction> transactionList = new ArrayList<>();

            try {
                FileReader file = new FileReader(ContextAsync.getFilesDir()+FILENAME);
                BufferedReader input = new BufferedReader(file);
                // do reading, usually loop until end of file reading
                String mLine;
                while ((mLine = input.readLine()) != null) {
                    JSONObject transactionJson = new JSONObject(mLine);
                    String date = transactionJson.getString("Date");
                    int transactionID = transactionJson.getInt("ID");

                    JSONObject itemJson = transactionJson.getJSONObject("Item");
                    String itemName = itemJson.getString("Name");
                    int itemID = itemJson.getInt("ID");
                    int quantity = itemJson.getInt("Quantity");
                    double price = itemJson.getDouble("Price");

                    JSONObject storeJson = transactionJson.getJSONObject("Store");
                    String storeName = storeJson.getString("Name");
                    int storeID = storeJson.getInt("ID");

                    Location location = null;
                    Item item = new Item(itemID,itemName,price,quantity);
                    Store store = new Store(storeID,storeName,location);

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    calendar.setTime(sdf.parse(date));// all done

                    Transaction transaction = new Transaction(transactionID, item, store, calendar);

                    transactionList.add(transaction);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return transactionList;
        }

        @Override
        protected void onPostExecute(ArrayList<Transaction> transactionList) {
            ArrayList<Transaction> transactions = new ArrayList<>();

            int size = transactionList.size();

            while (true) {
                size--;
                transactions.add(transactionList.get(size));
                if (size == 0) {
                    break;
                }
            }
            
            JsonControllerListener jsListener = (JsonControllerListener) ContextAsync;
            jsListener.updateNavigationItems(transactionList);
        }
    }
}
