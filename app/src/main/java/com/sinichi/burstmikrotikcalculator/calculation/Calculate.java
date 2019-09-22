package com.sinichi.burstmikrotikcalculator.calculation;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Calculate {

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

    public static void clearAll(EditText edt1, EditText edt2, EditText edt3, TextView tvResult1, TextView tvResult2) {
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
}
