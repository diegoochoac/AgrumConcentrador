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
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.concentrador.agrum.agrumconcentrador.R;
import com.concentrador.agrum.agrumconcentrador.database.DatabaseHelper;
import com.concentrador.agrum.agrumconcentrador.database.Terreno;
import com.concentrador.agrum.agrumconcentrador.database.Usuario;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class MenuFragment2 extends Fragment implements OnClickListener{


    private DatabaseHelper databaseHelper = null;

    private EditText codigo, hacienda, suerte, variedad, zona, area;
    private Button Btnagregar;



    public MenuFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.menu_fragment2, container, false);
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

    private void inicializarComponentes(final View view) {
        codigo = (EditText)view.findViewById(R.id.cmpCodigo);
        hacienda = (EditText)view.findViewById(R.id.cmpHacienda);
        suerte = (EditText)view.findViewById(R.id.cmpSuerte);
        variedad = (EditText)view.findViewById(R.id.cmpVariedad);
        zona = (EditText)view.findViewById(R.id.cmpZona);
        area = (EditText)view.findViewById(R.id.cmpArea);


        Btnagregar= (Button)view.findViewById(R.id.btnAgregarTerreno);
        Btnagregar.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Log.i("MenuFragment2", "onClick");
        switch (view.getId()) {
            case R.id.btnAgregarTerreno:
                agregarTerreno(
                        codigo.getText().toString(),
                        hacienda.getText().toString(),
                        suerte.getText().toString(),
                        variedad.getText().toString(),
                        zona.getText().toString(),
                        area.getText().toString()
                );
                Toast.makeText(view.getContext(),"Terreno Agregado", Toast.LENGTH_SHORT).show();
                limpiarCampos();
                break;

        }
    }

    public void agregarTerreno(String codigo,String hacienda, String suerte,String variedad, String zona, String area){
        Terreno terreno = new Terreno(codigo, hacienda,suerte,variedad,zona,area);
        try {
            // Now, need to interact with StudentDetails table/object, so initialize DAO for that
            final Dao<Terreno, Integer> terrenoDao = getHelper().getTerrenoDao();
            terrenoDao.create(terreno);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void limpiarCampos(){
        codigo.getText().clear();
        hacienda.getText().clear();
        suerte.getText().clear();
        variedad.getText().clear();
        zona.getText().clear();
        area.getText().clear();
    }
}