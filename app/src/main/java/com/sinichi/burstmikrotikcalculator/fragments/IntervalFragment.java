package com.sinichi.burstmikrotikcalculator.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinichi.burstmikrotikcalculator.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class IntervalFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_interval, container, false);

        return root;
    }
}