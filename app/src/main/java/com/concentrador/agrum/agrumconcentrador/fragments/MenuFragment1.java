package com.concentrador.agrum.agrumconcentrador.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.concentrador.agrum.agrumconcentrador.R;

public class MenuFragment1 extends Fragment {

    public MenuFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.menu_fragment1, container, false);
    }
}