package com.sinichi.burstmikrotikcalculator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinichi.burstmikrotikcalculator.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class BurstLimitFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_burst_limit, container, false);

        return root;
    }
}