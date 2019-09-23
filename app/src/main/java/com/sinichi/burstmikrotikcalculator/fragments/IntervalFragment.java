package com.sinichi.burstmikrotikcalculator.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.sinichi.burstmikrotikcalculator.R;
import com.sinichi.burstmikrotikcalculator.calculation.Calculate;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class IntervalFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, final Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_interval, container, false);

        final ConstraintLayout constraintLayout = root.findViewById(R.id.constrainLayout);
        final EditText edtThreshold = root.findViewById(R.id.edt_threshold);
        final EditText edtBurstTime = root.findViewById(R.id.edt_burst_time);
        final EditText edtBurstLimit = root.findViewById(R.id.edt_burst_limit);
        Button btnHitung = root.findViewById(R.id.btn_hitung);
        Button btnClear = root.findViewById(R.id.btn_clear);
        final TextView tvIntervalResult = root.findViewById(R.id.tv_result_interval);
        final TextView tvMaxLimitResult = root.findViewById(R.id.tv_result_max_limit);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Calculate.isValidForm(edtThreshold, edtBurstTime,
                        edtBurstLimit)) {
                    int threshold = Integer.parseInt(edtThreshold.getText().toString());
                    int burstTime = Integer.parseInt(edtBurstTime.getText().toString());
                    int burstLimit = Integer.parseInt(edtBurstLimit.getText().toString());
                    String interval = "Interval\n\n" + Calculate.interval(threshold,
                            burstTime, burstLimit) + "\ndetik";
                    String maxLimit = "Max Limit\n\n" + Calculate.MaxLimit(threshold) + "\nKbps";
                    tvIntervalResult.setText(interval);
                    tvMaxLimitResult.setText(maxLimit);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        Calculate.hideKeyboardOnConstraintLayout(constraintLayout, Objects.requireNonNull(getActivity()));
                    }
                } else {
                    Snackbar.make(root, "Take a look at blank form",
                            Snackbar.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        Calculate.hideKeyboardOnConstraintLayout(constraintLayout, Objects.requireNonNull(getActivity()));
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate.clearAll(savedInstanceState, edtThreshold, edtBurstTime, edtBurstLimit,
                        tvIntervalResult, tvMaxLimitResult);
            }
        });

        return root;
    }
}