package com.concentrador.agrum.agrumconcentrador.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.concentrador.agrum.agrumconcentrador.R;

public class MenuFragment1 extends Fragment implements View.OnClickListener {

    private EditText nombre, telefono, labor;
    private ImageView foto;
    private Button Btnagregar;

    public MenuFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.menu_fragment1, container, false);
        inicializarComponentes(rootview);
        return rootview;
    }

    private void inicializarComponentes(final View view) {
        nombre = (EditText)view.findViewById(R.id.cmpNombre);
        telefono = (EditText)view.findViewById(R.id.cmpTelefono);
        labor = (EditText)view.findViewById(R.id.cmpLabor);
        foto = (ImageView)view.findViewById(R.id.imageView);

        Btnagregar= (Button)view.findViewById(R.id.btnAgregar);
    }


    @Override
    public void onClick(View view) {
        Log.i("MenuFragment1", "onClick");
        if(view == Btnagregar){
            String mesg = String.format("%s Se ha presionado Boton");
            Toast.makeText(view.getContext(),mesg, Toast.LENGTH_SHORT).show();
        }

    }
}