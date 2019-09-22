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

import static com.sinichi.burstmikrotikcalculator.calculation.Calculate.threshold;

public class ThresholdFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_threshold, container, false);

        // TODO: Add savedInstanceState to prevent data loss when rotating screen
        // Init components
        final EditText edtBurstLimit = root.findViewById(R.id.edt_burst_limit);
        final EditText edtInterval = root.findViewById(R.id.edt_interval);
        final EditText edtBurstTime = root.findViewById(R.id.edt_burst_time);
        final ConstraintLayout constraintLayout = root.findViewById(R.id.constrainLayout);
        Button btnHitung = root.findViewById(R.id.btn_hitung);
        Button btnClear = root.findViewById(R.id.btn_clear);
        final TextView tvResultThreshold = root.findViewById(R.id.tv_result_threshold);
        final TextView tvResultMaxLimit = root.findViewById(R.id.tv_result_max_limit);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Calculate.isValidForm(edtBurstLimit,
                        edtInterval,
                        edtBurstTime)) {
                    int burstLimit = Integer.parseInt(edtBurstLimit.getText().toString());
                    int interval = Integer.parseInt(edtInterval.getText().toString());
                    int burstTime = Integer.parseInt(edtBurstTime.getText().toString());
                    String threshold = "Threshold\n\n" + threshold(burstLimit, interval, burstTime) + "\nKbps";
                    String maxLimit = "MaxLimit\n\n" + Calculate.MaxLimit(threshold(burstLimit, interval, burstTime)) + "\nKbps";
                    tvResultThreshold.setText(threshold);
                    tvResultMaxLimit.setText(maxLimit);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        Calculate.hideKeyboard(constraintLayout, Objects.requireNonNull(getActivity()));
                    }
                } else {
                    Snackbar.make(root, "Pay attention to blank form!", Snackbar.LENGTH_SHORT)
                            .show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        Calculate.hideKeyboard(constraintLayout, Objects.requireNonNull(getActivity()));
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate.clearAll(edtBurstLimit, edtInterval, edtBurstTime, tvResultThreshold, tvResultMaxLimit);
            }
        });

        return root;
    }
}