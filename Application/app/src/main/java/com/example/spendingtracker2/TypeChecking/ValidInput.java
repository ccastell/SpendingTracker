package com.example.spendingtracker2.TypeChecking;

import android.text.TextUtils;
import android.widget.EditText;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * Created by carlcastello on 16/03/17.
 */

public class ValidInput {
    public Boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Boolean isBoxFiled(String string) {
        if (string.trim().length() > 0 || ! string.matches("")) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

}
