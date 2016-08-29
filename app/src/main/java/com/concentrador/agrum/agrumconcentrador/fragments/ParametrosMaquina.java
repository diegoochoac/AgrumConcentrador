package com.concentrador.agrum.agrumconcentrador.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.concentrador.agrum.agrumconcentrador.R;

public class ParametrosMaquina extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.parametros_maquina_fragment, container, false);
    }
}