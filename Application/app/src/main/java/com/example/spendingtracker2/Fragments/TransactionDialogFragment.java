package com.example.spendingtracker2.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spendingtracker2.Interfaces.OnCompleteListener;
import com.example.spendingtracker2.R;

/**
 * Created by carlcastello on 16/03/17.
 */

public class TransactionDialogFragment extends DialogFragment {
    private OnCompleteListener myListener;

    @Override
    public void onAttach(Context contex) {
        super.onAttach(contex);
        try {
            this.myListener = (OnCompleteListener) contex;
        }
        catch (final  ClassCastException e) {
            throw new ClassCastException(contex.toString() + "Must Implement OnCompleteListener!!");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View view = inflater.inflate(R.layout.dialog_transaction_info, null);
        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Proceed to store info
                        String item_name = view.findViewById(R.id.item_name).toString();
                        String store_name = view.findViewById(R.id.store_name).toString();
                        double price = Double.parseDouble(view.findViewById(R.id.item_price).toString());
                        int quantity = Integer.parseInt(view.findViewById(R.id.item_quantity).toString());
                        myListener.onComplete(item_name,store_name,price,quantity);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        TransactionDialogFragment.this.getDialog().cancel();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
