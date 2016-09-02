package com.concentrador.agrum.agrumconcentrador.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.concentrador.agrum.agrumconcentrador.R;
import com.concentrador.agrum.agrumconcentrador.database.Contratista;
import com.concentrador.agrum.agrumconcentrador.database.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by diego on 2/09/16.
 */
public class MenuCrearContratista extends Fragment implements OnClickListener{


    private DatabaseHelper databaseHelper = null;
    private EditText nombre;
    private Button Btnagregar;

    public MenuCrearContratista() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.menu_fragment_crearcontratista, container, false);
        inicializarComponentes(rootview);
        return rootview;
    }

    protected DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    private void inicializarComponentes(View view) {

        nombre = (EditText)view.findViewById(R.id.cmpNombreContratista);
        Btnagregar= (Button)view.findViewById(R.id.btnAgregarContratista);
        Btnagregar.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnAgregarContratista:
                agregarContratista(nombre.getText().toString());
                Toast.makeText(view.getContext(),"Contratista Agregado", Toast.LENGTH_SHORT).show();
                limpiarCampos();
                break;
        }

    }

    private void agregarContratista(String name) {
        Contratista contratista = new Contratista(name);
        try {
            // Now, need to interact with StudentDetails table/object, so initialize DAO for that
            final Dao<Contratista, Integer> contratistaDao = getHelper().getContratistaDao();
            contratistaDao.create(contratista);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void limpiarCampos(){
        nombre.getText().clear();
        nombre.requestFocus();
    }
}
