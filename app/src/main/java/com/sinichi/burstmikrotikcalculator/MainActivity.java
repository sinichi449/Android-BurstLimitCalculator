package com.sinichi.burstmikrotikcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edtBurstLimit;
    private EditText edtIntervalTime;
    private EditText edtBurstTime;
    private Spinner spSatuanBurst;
    private TextView tvResultThreshold;
    private TextView tvResultMaxLimit;
    private Button btnHitung;
    private String satuan = "Kbps";
    private ConstraintLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        // TODO : Add spinner
        mainLayout = findViewById(R.id.mainLayout);
        edtBurstLimit = findViewById(R.id.edt_burst_limit);
        edtIntervalTime = findViewById(R.id.edt_interval_time);
        edtBurstTime = findViewById(R.id.edt_burst_time);
        spSatuanBurst = findViewById(R.id.sp_satuan_burst);
        tvResultThreshold = findViewById(R.id.tv_result_threshold);
        tvResultMaxLimit = findViewById(R.id.tv_result_max_limit);
        btnHitung = findViewById(R.id.btn_hitung);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thresholdResult = calculateThreshold() + "\n" + satuan;
                String maxLimitResult = calculateMaxLimit() + "\n" + satuan;
                tvResultThreshold.setText(thresholdResult);
                tvResultMaxLimit.setText(maxLimitResult);

                // Hide keyboard after click Button
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(mainLayout.getWindowToken(), 0);
                }
            }
        });
    }

    private int calculateThreshold() {
        int x = Integer.parseInt(edtBurstLimit.getText().toString());
        int n = Integer.parseInt(edtIntervalTime.getText().toString());
        int z = Integer.parseInt(edtBurstTime.getText().toString());
        return  (x*n)/z;
    }

    private int calculateMaxLimit() {
        return (4 * calculateThreshold()) / 3;
    }
}
