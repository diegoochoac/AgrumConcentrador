package com.concentrador.agrum.agrumconcentrador.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.concentrador.agrum.agrumconcentrador.R;
import com.concentrador.agrum.agrumconcentrador.database.DatabaseHelper;
import com.concentrador.agrum.agrumconcentrador.database.Usuario;
import com.concentrador.agrum.agrumconcentrador.utils.UsuarioAdapter;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.types.StringBytesType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MenuFragment3 extends Fragment implements View.OnClickListener {

    private DatabaseHelper databaseHelper = null;
    private Button BtnUsuarios, BtnTerreno;
    private ListView listview;

    private Dao<Usuario, Integer> usuarioDao;
    private List<Usuario> usuarioList;
    private UsuarioAdapter adapter = null;

    Context thiscontext;

    public MenuFragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.menu_fragment3, container, false);
        setHasOptionsMenu(true);        //habilitar el action Bar para tener botones
        inicializarComponentes(rootview);
        thiscontext = container.getContext();
        return rootview;
    }

    private DatabaseHelper getHelper() {
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

        listview = (ListView) view.findViewById(R.id.listViewfragment);

        BtnUsuarios = (Button)view.findViewById(R.id.btnUsuarios);
        BtnTerreno = (Button)view.findViewById(R.id.btnTerreno);
        BtnUsuarios.setOnClickListener(this);
        BtnTerreno.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onClick(View view) {
        Log.i("MenuFragment3", "onClick");

        switch (view.getId()) {
            case R.id.btnUsuarios:

                try {

                    usuarioDao =  getHelper().getUsuarioDao();
                    usuarioList = usuarioDao.queryForAll();
                    adapter = new UsuarioAdapter(thiscontext, R.layout.row, usuarioList);
                    listview.setAdapter(adapter);

                    Log.i("MenuFragment3", "inicializarComponentes");


                }catch (SQLException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.btnTerreno:
                break;
        }
    }


}