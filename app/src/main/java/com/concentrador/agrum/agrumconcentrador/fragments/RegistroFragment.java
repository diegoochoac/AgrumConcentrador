package com.concentrador.agrum.agrumconcentrador.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
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

    //Variables que se van hacia el MAINACTIVITY
    public static final String BTN_INIT_REGISTRO = "init registro";
    public static final String BTN_END_REGISTRO = "end registro";
    public static final String BTN_PARAMETROS = "parametros";

    //Variables que se traen de el MAINACTIVITY
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //Variables locales
    private String mParam1;
    private String mParam2;

    private int stateRegistro;

    private OnFragmentInteractionListener mListener;

    public static RegistroFragment newInstance(String param1, String param2) {
        RegistroFragment fragment = new RegistroFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public RegistroFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stateRegistro = 0;
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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
                break;
            case 1:
                btnRegistroIniciarRegistro.setText("Pulsa para\nTerminar Registro");
                btnRegistroIniciarRegistro.setBackgroundColor(Color.parseColor("#65E892"));
                break;
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            mListener = (OnFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        Uri uri = Uri.parse("");
        switch (view.getId()) {
            case R.id.btnIniciarRegistro:
                switch (stateRegistro) {
                    case 0:
                        uri = Uri.parse(BTN_INIT_REGISTRO+":");
                        btnRegistroIniciarRegistro.setText("Pulsa para\nTerminar Registro");
                        btnRegistroIniciarRegistro.setBackgroundColor(Color.parseColor("#65E892"));
                        stateRegistro = 1;
                        break;
                    case 1:
                        uri = Uri.parse(BTN_END_REGISTRO+":");
                        btnRegistroIniciarRegistro.setText("Pulsa para\nIniciar Registro");
                        btnRegistroIniciarRegistro.setBackgroundColor(Color.parseColor("#F75B5B"));
                        stateRegistro = 0;
                        break;
                }
                mListener.onFragmentInteraction(uri);
                break;

            case R.id.btnSetCero:
                uri = Uri.parse(NodosFragment.BTN_SET_CERO+":");
                mListener.onFragmentInteraction(uri);
                break;

            case R.id.btnParametros:
                uri = Uri.parse(BTN_PARAMETROS+":");
                mListener.onFragmentInteraction(uri);
                break;

        }


    }
}
