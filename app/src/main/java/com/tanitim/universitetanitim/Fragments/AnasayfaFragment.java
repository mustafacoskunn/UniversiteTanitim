package com.tanitim.universitetanitim.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanitim.universitetanitim.R;


public class AnasayfaFragment extends Fragment {


    public AnasayfaFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_anasayfa, container, false);
    }

}
