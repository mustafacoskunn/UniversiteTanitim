package com.tanitim.universitetanitim.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tanitim.universitetanitim.Activity.DetayActivity;
import com.tanitim.universitetanitim.Models.Universiteler;
import com.tanitim.universitetanitim.R;


public class DetayBilgiFragment extends Fragment {

    public DetayBilgiFragment() {
        // Required empty public constructor
    }
    Universiteler universite;
    private TextView bilgi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmeant
        View view = inflater.inflate(R.layout.fragment_detay_bilgi, container, false);

        universite = (Universiteler) getActivity().getIntent().getSerializableExtra("nesne");
        bilgi=view.findViewById(R.id.bilgi);
        bilgi.setText(universite.getToplamkadin());


        return view;

    }




}
