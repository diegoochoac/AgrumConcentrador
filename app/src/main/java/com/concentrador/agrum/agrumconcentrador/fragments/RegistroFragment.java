package com.concentrador.agrum.agrumconcentrador.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.concentrador.agrum.agrumconcentrador.R;

/**
 * Created by diego on 29/08/16.
 */
public class RegistroFragment extends Fragment implements OnClickListener{

    private TextView txtRegistroVelocidad;
    private TextView txtRegistroProfundidad;
    private TextView txtRegistroEstado;
    private TextView txtRegistroFecha;
    private TextView txtRegistroIniciarRegistro;

    private Button btnRegistroIniciarRegistro;
    private Button btnRegistroSetCero;
    private Button btnRegistroParametros;

    private int stateRegistro;

    public RegistroFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.registro_fragment, container, false);
        inicializarComponentes(rootview);
        return rootview;
    }


    private void inicializarComponentes(final View view) {

        txtRegistroVelocidad = (TextView)view.findViewById(R.id.txtRegistroVelocidad);
        txtRegistroProfundidad = (TextView)view.findViewById(R.id.txtRegistroProfundidad);
        txtRegistroEstado = (TextView)view.findViewById(R.id.txtRegistroEstado);
        txtRegistroFecha = (TextView)view.findViewById(R.id.txtRegistroFecha);

        btnRegistroIniciarRegistro = (Button)view.findViewById(R.id.btnIniciarRegistro);
        btnRegistroIniciarRegistro.setOnClickListener(this);

        btnRegistroSetCero= (Button)view.findViewById(R.id.btnSetCero);
        btnRegistroSetCero.setOnClickListener(this);

        btnRegistroParametros= (Button)view.findViewById(R.id.btnParametros);
        btnRegistroParametros.setOnClickListener(this);

        switch (stateRegistro){
            case 0:
                btnRegistroIniciarRegistro.setText("Pulsa para\nIniciar Registro");
                btnRegistroIniciarRegistro.setBackgroundColor(Color.parseColor("#F75B5B"));
                //imgRegistro.setImageResource(R.drawable.red);
                break;
            case 1:
                btnRegistroIniciarRegistro.setText("Pulsa para\nTerminar Registro");
                btnRegistroIniciarRegistro.setBackgroundColor(Color.parseColor("#65E892"));
                //imgRegistro.setImageResource(R.drawable.green);
                break;
        }

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnIniciarRegistro:
                switch (stateRegistro) {
                    case 0:
                        btnRegistroIniciarRegistro.setText("Pulsa para\nTerminar Registro");
                        btnRegistroIniciarRegistro.setBackgroundColor(Color.parseColor("#65E892"));
                        stateRegistro = 1;
                        break;
                    case 1:
                        btnRegistroIniciarRegistro.setText("Pulsa para\nIniciar Registro");
                        btnRegistroIniciarRegistro.setBackgroundColor(Color.parseColor("#F75B5B"));
                        stateRegistro = 0;
                        break;
                }

            case R.id.btnSetCero:

                break;

            case R.id.btnParametros:

                break;

        }


    }
}
