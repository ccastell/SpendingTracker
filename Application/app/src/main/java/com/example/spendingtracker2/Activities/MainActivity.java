package com.example.spendingtracker2.Activities;

import android.Manifest;
import android.app.DialogFragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.spendingtracker2.Controllers.ItemListController;
import com.example.spendingtracker2.Controllers.StoreListController;
import com.example.spendingtracker2.Controllers.TransactionListController;
import com.example.spendingtracker2.Interfaces.OnCompleteListener;
import com.example.spendingtracker2.Models.Item;
import com.example.spendingtracker2.Models.Store;
import com.example.spendingtracker2.Models.StoreList;
import com.example.spendingtracker2.Models.Transaction;
import com.example.spendingtracker2.R;
import com.example.spendingtracker2.Fragments.TransactionDialogFragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author carlcastello
 * http://www.mobiledev.tips/2015/11/09/android-gps-locations/
 */

public class MainActivity extends AppCompatActivity implements OnCompleteListener {
    private static final String FILENAME = "file.sav";

    private TransactionListController transactionListController;
    private StoreListController storeListController;
    private ItemListController itemListController;

    private ArrayList<Transaction> jsonTransactionList;
    private ArrayList<Store> jsonStoreList;
    private ArrayList<Item> jsonItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
    }

    @Override
    protected void onResume() {
        final Button button = (Button) findViewById(R.id.transaction_dialog_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                DialogFragment newFragment = new TransactionDialogFragment();
                newFragment.show(getFragmentManager(), "dialog");
            }
        });
    }


    public void onComplete(String itemName, String storeName, Double price, int quantity) {
        Location location;

        Calendar calendar = Calendar.getInstance();
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Check Permission of location Use
        if (checkPermission()) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } else {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        Item item = new Item(itemName,price,quantity);
        Store store = new Store(storeName,location);
        Transaction transaction = new Transaction(item,store,calendar);
    }


    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Load the file from json
     */
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Transaction>>(){}.getType();
            Type listType2 = new TypeToken<ArrayList<Store>>(){}.getType();
            Type listType3 = new TypeToken<ArrayList<Item>>(){}.getType();

            this.jsonTransactionList = gson.fromJson(in,listType);
            this.jsonStoreList = gson.fromJson(in,listType2);
            this.jsonItemList = gson.fromJson(in,listType3);

            this.transactionListController = new TransactionListController(this.jsonTransactionList);
            this.storeListController = new StoreListController(this.jsonStoreList);
            this.itemListController = new ItemListController(this.jsonItemList);
            //System.out.println(this.jsonList.size());

        } catch (FileNotFoundException e) {
			/* Create a brand new tweet list if we can't find the file. */
            this.transactionListController = new TransactionListController();
            this.storeListController = new StoreListController();
            this.itemListController = new ItemListController();
        }
    }

}
