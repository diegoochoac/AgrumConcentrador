package com.concentrador.agrum.agrumconcentrador.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.concentrador.agrum.agrumconcentrador.R;

public class ParametrosMaquinaFragment extends Fragment implements TextWatcher, OnClickListener {

    private TextView txtDistanciaHerramienta, txtDistanciaSuelo, txtAnchoLabor;
    private Button btnParametrosRegistro,btnParametrosParamRegistro;

    //Variables que se van hacia el MAINACTIVITY
    public final static String SET_DISTANCIA_HERRAMIENTA = "Distacia herramienta";
    public final static String SET_DISTANCIA_EJE_SUELO = "Distacia suelo";
    public final static String SET_MAQUINARIA = "Distacia maquinaria";
    public final static String SET_ANCHO_LABOR = "Distacia ancho labor";
    public final static String SET_PROFUNDIDAD_DESEADA = "Profundidad deseada";
    public static final String BTN_REGISTRO_MAQUINA = "Registro desde maquina";
    public static final String BTN_PARAMETROS_REGISTRO = "Parametros registros";

    //Variables que se traen de el MAINACTIVITY
    public final static String ARG_DISTANCIA_HERRAMIENTA = "Arg distancia herramienta";
    public final static String ARG_DISTANCIA_EJE_SUELO = "Arg distancia suelo";
    public final static String ARG_MAQUINARIA = "Arg maquinaria";
    public final static String ARG_ANCHO_LABOR = "Arg ancho labor";

    //Variables locales
    private String distancia_herramienta;
    private String distancia_suelo;
    private String ancho_labor;

    private OnFragmentInteractionListener mListener;

    Context thiscontext;

    public static ParametrosMaquinaFragment newInstance(String mDistanciaHerramienta, String mDistanciaSuelo,String mMaquinaria,String mAnchoLabor) {
        ParametrosMaquinaFragment fragment = new ParametrosMaquinaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DISTANCIA_HERRAMIENTA, mDistanciaHerramienta);
        args.putString(ARG_DISTANCIA_EJE_SUELO, mDistanciaSuelo);
        args.putString(ARG_MAQUINARIA, mMaquinaria);
        args.putString(ARG_ANCHO_LABOR, mAnchoLabor);
        fragment.setArguments(args);
        return fragment;
    }

    public ParametrosMaquinaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            distancia_herramienta = getArguments().getString(ARG_DISTANCIA_HERRAMIENTA);
            distancia_suelo = getArguments().getString(ARG_DISTANCIA_EJE_SUELO);
            //maquinaria = getArguments().getString(ARG_MAQUINARIA);
            ancho_labor = getArguments().getString(ARG_ANCHO_LABOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.parametros_maquina_fragment, container, false);
        inicializarComponentes(rootview);
        thiscontext = container.getContext();
        return rootview;
    }

    private void inicializarComponentes(final View view) {
        txtDistanciaHerramienta = (TextView)view.findViewById(R.id.txtParametrosDistanciaHerramienta);
        txtDistanciaSuelo = (TextView)view.findViewById(R.id.txtParametrosDistanciaSuelo);
        txtAnchoLabor = (TextView)view.findViewById(R.id.txtParametrosAnchoLabor);

        txtDistanciaHerramienta.setText(distancia_herramienta);
        txtDistanciaSuelo.setText(distancia_suelo);
        txtAnchoLabor.setText(ancho_labor);

        txtDistanciaHerramienta.addTextChangedListener(this);
        txtDistanciaSuelo.addTextChangedListener(this);
        txtAnchoLabor.addTextChangedListener(this);

        btnParametrosRegistro = (Button) view.findViewById(R.id.btnParamMaquina);
        btnParametrosRegistro.setOnClickListener(this);

        btnParametrosParamRegistro = (Button) view.findViewById(R.id.btnParametrosRegistro);
        btnParametrosParamRegistro.setOnClickListener(this);
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
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (txtDistanciaHerramienta.getText().hashCode() == s.hashCode())
        {
            try
            {
                double distanceTool = Double.parseDouble(s.toString());
                Uri uri = Uri.parse(SET_DISTANCIA_HERRAMIENTA+":"+distanceTool);
                mListener.onFragmentInteraction(uri);
                //display.setText("New Distance 2: "+distanceGrownd+"\n"+display.getText());
            }
            catch (Exception e)
            {
                //display.setText(e.getMessage()+"\n"+display.getText());
            }
        }
        else if (txtDistanciaSuelo.getText().hashCode() == s.hashCode())
        {
            try
            {
                double distanceTool = Double.parseDouble(s.toString());
                Uri uri = Uri.parse(SET_DISTANCIA_EJE_SUELO+":"+distanceTool);
                mListener.onFragmentInteraction(uri);
                //display.setText("New Distance 2: "+distanceGrownd+"\n"+display.getText());
            }
            catch (Exception e)
            {
                //display.setText(e.getMessage()+"\n"+display.getText());
            }
        }
        else if (txtAnchoLabor.getText().hashCode() == s.hashCode())
        {
            try
            {
                double distanceTool = Double.parseDouble(s.toString());
                Uri uri = Uri.parse(SET_ANCHO_LABOR+":"+distanceTool);
                mListener.onFragmentInteraction(uri);
                //display.setText("New Distance 2: "+distanceGrownd+"\n"+display.getText());
            }
            catch (Exception e)
            {
                //display.setText(e.getMessage()+"\n"+display.getText());
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {
        Log.i("ParametrosMaquina", "onClick");
        Uri uri = Uri.parse("");
        switch (view.getId()) {
            case R.id.btnParamMaquina:
                uri = Uri.parse(BTN_REGISTRO_MAQUINA+":");
                mListener.onFragmentInteraction(uri);
                break;
            case R.id.btnParametrosRegistro:
                uri = Uri.parse(BTN_PARAMETROS_REGISTRO+":");
                mListener.onFragmentInteraction(uri);
                break;
        }
    }
}