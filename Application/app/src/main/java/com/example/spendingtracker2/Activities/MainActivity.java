package com.example.spendingtracker2.Activities;

import android.Manifest;
import android.app.DialogFragment;

import android.app.FragmentManager;

import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.spendingtracker2.Controllers.ItemController;
import com.example.spendingtracker2.Controllers.TransactionController;
import com.example.spendingtracker2.Controllers.TransactionListController;
import com.example.spendingtracker2.Fragments.CameraFragment;
import com.example.spendingtracker2.Fragments.TransactionDialogFragment;
import com.example.spendingtracker2.Fragments.TransactionInformationFragment;
import com.example.spendingtracker2.Interfaces.JsonControllerListener;
import com.example.spendingtracker2.Interfaces.OnCompleteListener;
import com.example.spendingtracker2.Models.Item;
import com.example.spendingtracker2.Models.Store;
import com.example.spendingtracker2.Models.Transaction;
import com.example.spendingtracker2.Models.TransactionList;
import com.example.spendingtracker2.R;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        OnCompleteListener, JsonControllerListener {

    private Menu nav_Menu;
    private NavigationView navigationView;
    private Location location;
    private LocationManager locationManager;
    private GoogleApiClient mGoogleApiClient;

    private TransactionList transactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create an instance of GoogleAPIClient.
        if (this.mGoogleApiClient == null) {
            this.mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


        this.navigationView = (NavigationView) findViewById(R.id.nav_view);
        this.navigationView.setNavigationItemSelectedListener(this);
        this.nav_Menu = this.navigationView.getMenu();

        // Clear The recent when it's empty
        this.nav_Menu.findItem(R.id.nav_recent_1).setVisible(false);
        this.nav_Menu.findItem(R.id.nav_recent_2).setVisible(false);
        this.nav_Menu.findItem(R.id.nav_recent_3).setVisible(false);
        this.nav_Menu.findItem(R.id.nav_recent_4).setVisible(false);
        this.nav_Menu.findItem(R.id.nav_recent_5).setVisible(false);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("MMMM dd, yyyy");
        String formattedTime = format.format(calendar.getTime());
        TextView subHeader = (TextView) this.navigationView.getHeaderView(0).findViewById(R.id.date_view);
        subHeader.setText(formattedTime);

        // Update Side Navigation
        TransactionListController tlc = new TransactionListController(this);
        tlc.updateSideNavigation();


        // http://stackoverflow.com/questions/36583205/open-fragment-from-activity
        FragmentManager fragmentManager = MainActivity.this.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        CameraFragment fragment = new CameraFragment();
        fragmentTransaction.replace(R.id.main_fragment_container,fragment); //ERROR ON THIS LINE
        fragmentTransaction.commit();
        // this.mGoogleApiClient.connect();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all) {

        } else if (id == R.id.nav_scheduled) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_recent_1) {
//            setContentView(R.layout.activity_transaction);
//            Intent intent = new Intent(this, TransactionActivity.class);
//            startActivity(intent);
        } else if (id == R.id.nav_recent_2) {

        } else if (id == R.id.nav_recent_3) {

        } else if (id == R.id.nav_recent_4) {

        } else if (id == R.id.nav_recent_5) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        this.mGoogleApiClient.connect();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    protected void onResume() {
        super.onResume();

//        final Button button = (Button) findViewById(R.id.transaction_dialog_button);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//                DialogFragment newFragment = new TransactionDialogFragment();
//                newFragment.show(getFragmentManager(), "dialog");
//            }
//        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    }

    /**
     * A function responsible for processing the user input
     * from the Dialog Box
     * @param itemName
     * @param storeName
     * @param price
     * @param quantity
     */
    @Override
    public void onComplete(String itemName, String storeName, Double price, int quantity) {

        Calendar calendar = Calendar.getInstance();
        Item item = new Item(itemName, price, quantity);
        Store store = new Store(storeName, this.location);

        Transaction transaction = new Transaction(item,store,calendar);
        TransactionController tController = new TransactionController(this,transaction);
        tController.save();
//        mGoogleApiClient.disconnect();
//
    }


    /**
     * Update the sidebar list with the return list of jsonController
     * http://stackoverflow.com/questions/28729067/android-increment-button-in-loop-to-setvisibility
     * @param transactions
     */
    @Override
    public void updateNavigationItems(ArrayList<Transaction> transactions) {
        double totalExpenditure = 0;
        // called by anyone other than GetJsonController
        if (transactions == null) {
            TransactionListController tlc = new TransactionListController(this);
            tlc.updateSideNavigation();
        } else {
            transactionList = new TransactionList(transactions);
            int size = (transactions.size() > 5) ? 5 : transactions.size();
            for (int i = 1; i <= size; i++){
                Transaction transaction = transactions.get(i - 1);
                TransactionController tc = new TransactionController(transaction);

                int itemId = this.getResources().getIdentifier("nav_recent_"+i, "id", this.getPackageName());
                this.nav_Menu.findItem(itemId).setVisible(true);
                this.nav_Menu.findItem(itemId).setTitle(tc.getTransactionDate() + " " + tc.getTransactionTime());

                ItemController ic = new ItemController(transaction.getItem());
                totalExpenditure += ic.getItemPrice();
            }
        }
        TextView header = (TextView) this.navigationView.getHeaderView(0).findViewById(R.id.price_view);
        header.setText("$ " + String.format("%.2f",totalExpenditure));

    }



    @Override
    public void onConnected(Bundle connectionHint) {
        Location mLastLocation = null;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
        }

        if (mLastLocation != null) {
            System.out.println(mLastLocation.getLatitude());
            this.location = mLastLocation;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

}
