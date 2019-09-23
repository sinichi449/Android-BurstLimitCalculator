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

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BurstTimeFragment extends Fragment {


    public BurstTimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_burst_time, container, false);

        final LinearLayout linearLayout = root.findViewById(R.id.linearLayout);
        final EditText edtBurstLimit = root.findViewById(R.id.edt_burst_limit);
        final EditText edtInterval = root.findViewById(R.id.edt_interval);
        final EditText edtThreshold = root.findViewById(R.id.edt_threshold);
        Button btnHitung = root.findViewById(R.id.btn_hitung);
        Button btnClear = root.findViewById(R.id.btn_clear);
        final TextView tvResultBurstTime = root.findViewById(R.id.tv_result_burst_time);
        final TextView tvResultMaxLimit = root.findViewById(R.id.tv_result_max_limit);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Calculate.isValidForm(edtBurstLimit, edtInterval,
                        edtThreshold)) {
                    int burstLIimit = Integer.parseInt(edtBurstLimit.getText().toString());
                    int interval = Integer.parseInt(edtInterval.getText().toString());
                    int threshold = Integer.parseInt(edtThreshold.getText().toString());
                    String burstTime = "Burst Time\n\n" + Calculate.burstTime(burstLIimit,
                            interval, threshold) + "\ndetik";
                    String maxLimit = "Max Limit\n\n" + Calculate.MaxLimit(threshold)
                            + "\nKbps";
                    tvResultBurstTime.setText(burstTime);
                    tvResultMaxLimit.setText(maxLimit);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        Calculate.hideKeyboardOnLinearLayout(linearLayout, Objects.requireNonNull(getActivity()));
                    }
                } else {
                    Snackbar.make(root, "Pay attention to blank form",
                            Snackbar.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        Calculate.hideKeyboardOnLinearLayout(linearLayout,
                                Objects.requireNonNull(getActivity()));
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate.clearAll(savedInstanceState, edtBurstLimit, edtInterval, edtThreshold,
                        tvResultBurstTime, tvResultMaxLimit);
            }
        });

        return root;
    }

}
