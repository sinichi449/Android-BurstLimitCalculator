package com.sinichi.burstmikrotikcalculator.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.sinichi.burstmikrotikcalculator.R;
import com.sinichi.burstmikrotikcalculator.calculation.Calculate;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class BurstLimitFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_burst_limit, container, false);

        final LinearLayout linearLayout = root.findViewById(R.id.linearLayout);
        final EditText edtThreshold = root.findViewById(R.id.edt_threshold);
        final EditText edtBurstTime = root.findViewById(R.id.edt_burst_time);
        final EditText edtInterval = root.findViewById(R.id.edt_interval);
        Button btnHitung = root.findViewById(R.id.btn_hitung);
        Button btnClear = root.findViewById(R.id.btn_clear);
        final TextView tvResultBurstLimit = root.findViewById(R.id.tv_result_burst_limit);
        final TextView tvResultMaxLimit = root.findViewById(R.id.tv_result_max_limit);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Calculate.isValidForm(edtThreshold, edtBurstTime,
                        edtInterval)) {
                    int threshold = Integer.parseInt(edtThreshold.getText().toString());
                    int burstTime = Integer.parseInt(edtBurstTime.getText().toString());
                    int interval = Integer.parseInt(edtInterval.getText().toString());
                    String burstLimit = "Burst Limit\n\n" + Calculate.burstLimit(threshold,
                            burstTime, interval) + "\nKbps";
                    String maxLimit = "Max Limit\n\n" + Calculate.MaxLimit(threshold) + "\nKbps";
                    tvResultBurstLimit.setText(burstLimit);
                    tvResultMaxLimit.setText(maxLimit);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        Calculate.hideKeyboardOnLinearLayout(linearLayout, Objects.requireNonNull(getActivity()));
                    }
                } else {
                    Snackbar.make(root, "Pay attention to blank form",
                            Snackbar.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        Calculate.hideKeyboardOnLinearLayout(linearLayout, Objects.requireNonNull(getActivity()));
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate.clearAll(edtBurstTime, edtInterval, edtThreshold,
                        tvResultBurstLimit, tvResultMaxLimit);
            }
        });

        return root;
    }
}