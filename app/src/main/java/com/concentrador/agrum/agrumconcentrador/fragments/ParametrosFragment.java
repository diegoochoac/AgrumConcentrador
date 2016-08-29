package com.concentrador.agrum.agrumconcentrador.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import com.concentrador.agrum.agrumconcentrador.R;

public class ParametrosFragment extends Fragment implements OnClickListener{



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.parametros_fragment, container, false);
        inicializarComponentes(rootview);
        return rootview;
    }

    private void inicializarComponentes(final View view) {
    }

    @Override
    public void onClick(View view) {

    }
}