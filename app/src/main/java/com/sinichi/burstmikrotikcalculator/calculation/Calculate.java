package com.sinichi.burstmikrotikcalculator.calculation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Calculate {

    public static final String BURST_LIMIT = "burstLimit";
    public static final String INTERVAL = "interval";
    public static final String BURST_TIME = "burstTime";
    public static final String MAX_LIMIT = "maxLimit";
    public static final String THRESHOLD = "threshold";

    public static int threshold(int burstLimit, int interval, int burstTime) {
        return (burstLimit * interval) / burstTime;
    }

    public static int interval(int threshold, int burstTime, int burstLimit) {
        return (threshold * burstTime) / burstLimit;
    }

    public static int burstLimit(int threshold, int burstTime, int interval) {
        return (threshold * burstTime) / interval;
    }

    public static int burstTime(int burstLimit, int interval, int threshold) {
        return (burstLimit * interval) / threshold;
    }

    public static int MaxLimit(int threshold) {
        return (4 * threshold) / 3;
    }

    public static void clearAll(Bundle savedInstance, EditText edt1, EditText edt2, EditText edt3, TextView tvResult1, TextView tvResult2) {
        savedInstance.clear();
        edt1.setText("");
        edt2.setText("");
        edt3.setText("");
        tvResult1.setText("");
        tvResult2.setText("");
    }

    public static boolean isValidForm(EditText edt1, EditText edt2, EditText edt3) {
        return !TextUtils.isEmpty(edt1.getText().toString()) &&
                !TextUtils.isEmpty(edt2.getText().toString())
                && !TextUtils.isEmpty(edt3.getText().toString());
    }

    public static void hideKeyboardOnConstraintLayout(ConstraintLayout constraintLayout, Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(constraintLayout.getWindowToken(), 0);
        }
    }

    public static void hideKeyboardOnLinearLayout(LinearLayout linearLayout, Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(linearLayout.getWindowToken(), 0);
        }
    }

    public static void saveInstance(Bundle savedInstance, EditText edt1, EditText edt2,
                                    EditText edt3, int result1, int result2, String keyForEdt1,
                                    String keyForEdt2, String keyForEdt3, String keyForResult1, String keyForResult2) {
        savedInstance.putInt(keyForEdt1, Integer.parseInt(edt1.getText().toString()));
        savedInstance.putInt(keyForEdt2, Integer.parseInt(edt2.getText().toString()));
        savedInstance.putInt(keyForEdt3, Integer.parseInt(edt3.getText().toString()));
        savedInstance.putInt(keyForResult1, result1);
        savedInstance.putInt(keyForResult2, result2);
    }
}
