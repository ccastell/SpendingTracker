package com.example.spendingtracker2.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.spendingtracker2.Interfaces.OnCompleteListener;
import com.example.spendingtracker2.Models.Item;
import com.example.spendingtracker2.Models.Store;
import com.example.spendingtracker2.Models.Transaction;
import com.example.spendingtracker2.R;
import com.example.spendingtracker2.TypeChecking.ValidInput;

/**
 * Created by carlcastello on 16/03/17.
 */

public class TransactionDialogFragment extends DialogFragment {
    private View view;
    public OnCompleteListener myListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // For sdk version less than 23
        // http://stackoverflow.com/questions/32077086/android-onattachcontext-not-called-for-api-23
        if (Build.VERSION.SDK_INT < 23) {
            onAttachToContext(getActivity());
        }

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        this.view = inflater.inflate(R.layout.dialog_transaction_info, null);
        builder.setView(view)
                // Add action buttons
                .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

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

    // http://stackoverflow.com/questions/13746412/prevent-dialogfragment-from-dismissing-when-button-is-clicked
    @Override
    public void onStart() {
        //super.onStart() is where dialog.show() is actually called on the underlying dialog, so we have to do it after this point
        super.onStart();

        final ValidInput validInput = new ValidInput();

        AlertDialog dialog = (AlertDialog) getDialog();
        if(dialog != null) {
            Button positiveButton = (Button) dialog.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Convert Input box to string
                    String itemName = ((EditText) view.findViewById(R.id.item_name)).getText().toString();
                    String storeName = ((EditText) view.findViewById(R.id.store_name)).getText().toString();
                    String valuePrice = ((EditText) view.findViewById(R.id.item_price)).getText().toString();
                    String valueQuantity = ((EditText) view.findViewById(R.id.item_quantity)).getText().toString();

                    // Check if input boxes are filed
                    if (validInput.isBoxFiled(itemName) && validInput.isBoxFiled(storeName) &&
                            validInput.isBoxFiled(valuePrice) && validInput.isBoxFiled(valueQuantity) &&
                            validInput.isDouble(valuePrice) && validInput.isInteger(valueQuantity) ) {

                        double price = Double.parseDouble(valuePrice);
                        int quantity = Integer.parseInt(valueQuantity);

                        // Call Interface... Send data back to Main Activity
                        myListener.onComplete(itemName,storeName,price,quantity);

                        // Dismiss if all fields are filed.
                        dismiss();
                    }
                }
            });
        }
    }

    // Only works for SDK version >= 23
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.myListener = (OnCompleteListener) context;
        } catch (final  ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "Must Implement OnCompleteListener!!");
        }
    }

    // For SDK < 23
    // http://stackoverflow.com/questions/32077086/android-onattachcontext-not-called-for-api-23
    private void onAttachToContext(Context context) {
        try {
            this.myListener = (OnCompleteListener) context;
        }
        catch (final  ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "Must Implement OnCompleteListener!!");
        }
    }
}

