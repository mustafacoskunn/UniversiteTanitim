package com.tanitim.universitetanitim.Fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.tanitim.universitetanitim.R;




public class KarsilastirFragment extends Fragment {


    public KarsilastirFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_karsilastir, container, false);
    }

}
