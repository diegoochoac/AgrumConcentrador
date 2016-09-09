package com.concentrador.agrum.agrumconcentrador.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.concentrador.agrum.agrumconcentrador.R;

public class NodosFragment extends Fragment implements TextWatcher, OnClickListener {

    private TextView txtNodosProfundidad,txtNodosAngTractor,txtNodosAngImplemento,txtNodosAngDiferencia,txtNodosAngCero,txtNodosAngMedicion, txtNodosOffset;
    private Button btnIniciarCom, btnSetCero;

    //Variables que se van hacia el MAINACTIVITY
    public static final String BTN_SET_CERO = "Set cero";
    public static final String BTN_INIT_COM = "init Com";
    public static final String BTN_END_COM = "end Com";
    public final static String SET_OFFSET_PROFUNDIDAD = "Offset profundidad";

    //Variables que se traen de el MAINACTIVITY
    private static final String ARG_OFFSET_PROFUNDIDAD = "Arg profundidad";
    private static final String ARG_ANGULO_CERO = "Para Ang cero";

    //Variables locales
    private String offset_profundidad;
    private String ang_Cero;
    private int stateComSerial;

    private OnFragmentInteractionListener mListener;

    public static NodosFragment newInstance(String nAngularCero,String nOffsetProfundidad )
    {
        NodosFragment fragment = new NodosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_OFFSET_PROFUNDIDAD, nOffsetProfundidad);
        args.putString(ARG_ANGULO_CERO, nAngularCero);
        fragment.setArguments(args);
        return fragment;
    }


    public NodosFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stateComSerial = 0;
        if (getArguments() != null) {
            offset_profundidad = getArguments().getString(ARG_OFFSET_PROFUNDIDAD);
            ang_Cero = getArguments().getString(ARG_ANGULO_CERO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.nodos_fragment, container, false);
        inicializarComponentes(rootview);
        return rootview;
    }

    private void inicializarComponentes(final View view) {

        txtNodosProfundidad = (TextView)view.findViewById(R.id.txtNodosProfundidad);
        txtNodosAngTractor = (TextView)view.findViewById(R.id.txtNodosAngTractor);
        txtNodosAngImplemento = (TextView)view.findViewById(R.id.txtNodosAngImplemento);
        txtNodosAngDiferencia = (TextView)view.findViewById(R.id.txtNodosaAngDiferencia);
        txtNodosAngCero = (TextView)view.findViewById(R.id.txtNodosAngCero);
        txtNodosAngMedicion = (TextView)view.findViewById(R.id.txtNodosAngMedicion);
        txtNodosOffset = (EditText)view.findViewById(R.id.txtNodosOffetMedicion);

        txtNodosOffset.setText(offset_profundidad);
        txtNodosAngCero.setText(ang_Cero);

        txtNodosOffset.addTextChangedListener(this);

        btnSetCero = (Button)view.findViewById(R.id.btnNodosSetCero);
        btnIniciarCom = (Button)view.findViewById(R.id.btnNodosInitCom);

        btnSetCero.setOnClickListener(this);
        btnIniciarCom.setOnClickListener(this);

        switch (stateComSerial){
            case 0:
                btnIniciarCom.setText("Iniciar Com");
                break;
            case 1:
                btnIniciarCom.setText("Terminar Com");
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
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
        if (txtNodosOffset.getText().hashCode() == s.hashCode())
        {
            Uri uri = Uri.parse(SET_OFFSET_PROFUNDIDAD +":"+ s.toString());
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onClick(View view) {
        Log.i("ParametrosFragment", "onClick");
        Uri uri = Uri.parse("");
        switch (view.getId()) {
            case R.id.btnNodosSetCero:
                uri = Uri.parse(BTN_SET_CERO+":");
                mListener.onFragmentInteraction(uri);
                break;
            case R.id.btnNodosInitCom:
                switch (stateComSerial)
                {
                    case 0:
                        btnIniciarCom.setText("Terminar Com");
                        uri = Uri.parse(BTN_INIT_COM+":");
                        stateComSerial = 1;
                        break;
                    case 1:
                        btnIniciarCom.setText("Iniciar Com");
                        uri = Uri.parse(BTN_END_COM+":");
                        stateComSerial = 0;
                        break;
                }
                mListener.onFragmentInteraction(uri);
                break;
        }
    }
}