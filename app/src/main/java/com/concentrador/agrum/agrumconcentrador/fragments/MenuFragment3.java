package com.concentrador.agrum.agrumconcentrador.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.concentrador.agrum.agrumconcentrador.R;
import com.concentrador.agrum.agrumconcentrador.database.DatabaseHelper;
import com.concentrador.agrum.agrumconcentrador.database.Terreno;
import com.concentrador.agrum.agrumconcentrador.database.Usuario;
import com.concentrador.agrum.agrumconcentrador.utils.TerrenoAdapter;
import com.concentrador.agrum.agrumconcentrador.utils.UsuarioAdapter;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MenuFragment3 extends Fragment implements View.OnClickListener {

    private DatabaseHelper databaseHelper = null;
    private Button BtnUsuarios, BtnTerreno;
    private ListView listview;

    private Dao<Usuario, Integer> usuarioDao;
    private Dao<Terreno, Integer> terrenoDao;
    private List<Usuario> usuarioList;
    private List<Terreno> terrenoList;
    private UsuarioAdapter adapterUsuario = null;
    private TerrenoAdapter adapterTerreno = null;

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
                    adapterUsuario = new UsuarioAdapter(thiscontext, R.layout.row, usuarioList);
                    listview.setAdapter(adapterUsuario);
                }catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnTerreno:
                try {
                    terrenoDao =  getHelper().getTerrenoDao();
                    terrenoList = terrenoDao.queryForAll();
                    adapterTerreno = new TerrenoAdapter(thiscontext, R.layout.row, terrenoList);
                    listview.setAdapter(adapterTerreno);
                }catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


}