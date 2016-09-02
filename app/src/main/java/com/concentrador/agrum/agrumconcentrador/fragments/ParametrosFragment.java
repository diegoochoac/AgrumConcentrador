package com.concentrador.agrum.agrumconcentrador.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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

public class ParametrosFragment extends Fragment implements OnClickListener{

    private DatabaseHelper databaseHelper = null;

    private TextView hacienda, suerte, contratista,operador;
    private ListView listview;

    private Dao<Usuario, Integer> usuarioDao;
    private Dao<Terreno, Integer> terrenoDao;
    private List<Usuario> usuarioList;
    private List<Terreno> terrenoList;
    private UsuarioAdapter adapterUsuario = null;
    private TerrenoAdapter adapterTerreno = null;

    private int stateClick = -1;
    Context thiscontext;

    public ParametrosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.parametros_fragment, container, false);
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

    private void inicializarComponentes(final View view) {

        listview = (ListView) view.findViewById(R.id.listViewfragmentparametros);
        hacienda = (TextView)view.findViewById(R.id.cmpTextHacienda);
        suerte = (TextView)view.findViewById(R.id.cmpTextSuerte);
        contratista = (TextView)view.findViewById(R.id.cmpTextContratista);
        operador = (TextView)view.findViewById(R.id.cmpTextOperador);

        hacienda.setOnClickListener(this);
        suerte.setOnClickListener(this);
        contratista.setOnClickListener(this);
        operador.setOnClickListener(this);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object select= null;

                switch (stateClick) {
                    case 1:
                        select= adapterTerreno.getItem(position).getHacienda();
                        hacienda.setText(select.toString());
                        stateClick = -1;
                        break;
                    case 2:
                        select= adapterTerreno.getItem(position).getSte();
                        suerte.setText(select.toString());
                        stateClick = -1;
                        break;
                    case 3:
                        select= adapterTerreno.getItem(position).getSte();
                        contratista.setText(select.toString());
                        stateClick = -1;
                        break;
                    case 4:
                        select= adapterUsuario.getItem(position).getUsuarioName();
                        operador.setText(select.toString());
                        stateClick = -1;
                        break;
                    default:
                        break;
                }
                Log.i("ParametrosFragment", "setOnItemClickListener +position:");
            }
        });

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
            case R.id.cmpTextHacienda:
                try {
                    terrenoDao = getHelper().getTerrenoDao();
                    terrenoList = terrenoDao.queryForAll();     //TODO: cambiar esto por la consulta adecuada
                    adapterTerreno = new TerrenoAdapter(thiscontext, R.layout.row, terrenoList);
                    listview.setAdapter(adapterTerreno);
                    stateClick = 1;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.cmpTextSuerte:
                try {
                    terrenoDao = getHelper().getTerrenoDao();
                    terrenoList = terrenoDao.queryForAll();     //TODO: cambiar esto por la consulta adecuada
                    adapterTerreno = new TerrenoAdapter(thiscontext, R.layout.row, terrenoList);
                    listview.setAdapter(adapterTerreno);
                    stateClick = 2;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.cmpTextOperador:
                try {
                    usuarioDao =  getHelper().getUsuarioDao();
                    usuarioList = usuarioDao.queryForAll();       //TODO: cambiar esto por la consulta adecuada
                    adapterUsuario = new UsuarioAdapter(thiscontext, R.layout.row, usuarioList);
                    listview.setAdapter(adapterUsuario);
                    stateClick = 4;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

        }
    }



}