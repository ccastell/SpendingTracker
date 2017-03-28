package com.example.spendingtracker2.Controllers;

import android.content.Context;

import com.example.spendingtracker2.Activities.MainActivity;
import com.example.spendingtracker2.Models.Transaction;

import java.util.ArrayList;

/**
 * Created by carlcastello on 16/03/17.
 */

public class TransactionListController {
    private Context context;

    public TransactionListController() {

    }

    public TransactionListController(Context context) {
        this.context = context;
    }

    public void updateSideNavigation() {
        GetJsonController.GetRecentTransactionTask getTransactionTask = new GetJsonController.GetRecentTransactionTask(this.context);
        getTransactionTask.execute();
    }
}
