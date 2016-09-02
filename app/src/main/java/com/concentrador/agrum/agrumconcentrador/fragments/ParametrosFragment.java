package com.concentrador.agrum.agrumconcentrador.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
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

    private Dao<Usuario, Integer> usuarioDao;
    private Dao<Terreno, Integer> terrenoDao;
    private List<Usuario> usuarioList;
    private List<Terreno> terrenoList;
    private UsuarioAdapter adapterUsuario = null;
    private TerrenoAdapter adapterTerreno = null;
    private Usuario usuario = new Usuario();

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

        hacienda = (TextView)view.findViewById(R.id.cmpTextHacienda);
        suerte = (TextView)view.findViewById(R.id.cmpTextSuerte);
        contratista = (TextView)view.findViewById(R.id.cmpTextContratista);
        operador = (TextView)view.findViewById(R.id.cmpTextOperador);

        hacienda.setOnClickListener(this);
        suerte.setOnClickListener(this);
        contratista.setOnClickListener(this);
        operador.setOnClickListener(this);



    }


    void AlerDialogListUsuario(){
        final AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(thiscontext);

        LinearLayout layout= new LinearLayout(thiscontext);
        final TextView Message        = new TextView(thiscontext);
        final EditText editText = new EditText(thiscontext);
        final ListView listview = new ListView(thiscontext);

        Message.setText("Ingrese busqueda:");
        editText.setSingleLine();
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(Message);
        layout.addView(editText);
        layout.addView(listview);
        alertdialogbuilder.setTitle("Por favor seleccione");
        alertdialogbuilder.setView(layout);

        listview.setAdapter(adapterUsuario);
        final AlertDialog alert = alertdialogbuilder.create();

        editText.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s){

            }
            public void beforeTextChanged(CharSequence s,
                                          int start, int count, int after){

            }
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String text = editText.getText().toString().toLowerCase().trim();
                try {
                usuarioDao =  getHelper().getUsuarioDao();
                usuarioList = usuarioDao.queryForEq(Usuario.NOMBRE,text);
                adapterUsuario = new UsuarioAdapter(thiscontext, R.layout.row, usuarioList);
                listview.setAdapter(adapterUsuario);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Object select= null;
                select= adapterUsuario.getItem(position).getUsuarioName();
                operador.setText(select.toString());
                alert.cancel();
            }
        });

        alertdialogbuilder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
    }

    void AlerDialogListTerreno(){
        final AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(thiscontext);

        LinearLayout layout= new LinearLayout(thiscontext);
        final TextView Message = new TextView(thiscontext);
        final EditText editText = new EditText(thiscontext);
        final ListView listview = new ListView(thiscontext);

        Message.setText("Ingrese busqueda:");
        editText.setSingleLine();
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(Message);
        layout.addView(editText);
        layout.addView(listview);
        alertdialogbuilder.setTitle("Por favor seleccione");
        alertdialogbuilder.setView(layout);

        listview.setAdapter(adapterTerreno);
        final AlertDialog alert = alertdialogbuilder.create();

        editText.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s){
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String text = editText.getText().toString().toLowerCase().trim();
                try {
                    terrenoDao =  getHelper().getTerrenoDao();

                    switch (stateClick){
                        case 1:
                            terrenoList = terrenoDao.queryForEq(Terreno.HACIENDA,text);
                            break;
                        case 2:
                            terrenoList = terrenoDao.queryForAll();
                            break;
                    }
                    adapterTerreno = new TerrenoAdapter(thiscontext, R.layout.row, terrenoList);
                    listview.setAdapter(adapterTerreno);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Object select= null;
                switch (stateClick){
                    case 1:
                        select= adapterTerreno.getItem(position).getHacienda();
                        hacienda.setText(select.toString());
                        break;
                    case 2:
                        select= adapterTerreno.getItem(position).getSte();
                        suerte.setText(select.toString());
                        break;
                }
                alert.cancel();
            }
        });

        alertdialogbuilder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.show();
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
                    terrenoList = terrenoDao.queryForAll();
                    adapterTerreno = new TerrenoAdapter(thiscontext, R.layout.row, terrenoList);
                    AlerDialogListTerreno();
                    stateClick = 1;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.cmpTextSuerte:
                try {
                    terrenoDao = getHelper().getTerrenoDao();
                    terrenoList = terrenoDao.queryForAll();
                    adapterTerreno = new TerrenoAdapter(thiscontext, R.layout.row, terrenoList);
                    AlerDialogListTerreno();
                    stateClick = 2;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.cmpTextOperador:
                try {
                    usuarioDao =  getHelper().getUsuarioDao();
                    usuarioList = usuarioDao.queryForAll();
                    adapterUsuario = new UsuarioAdapter(thiscontext, R.layout.row, usuarioList);
                    AlerDialogListUsuario();
                    stateClick = 4;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

        }
    }



}