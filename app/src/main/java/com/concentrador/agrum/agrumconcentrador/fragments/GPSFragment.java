package com.concentrador.agrum.agrumconcentrador.fragments;

import android.content.Context;
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
public class GPSFragment extends Fragment implements OnClickListener  {

    private TextView txtGPSLatitud, txtGPSLongitud, txtGPSAltitud, txtGPSVelocidad, txtGPSPrecision, txtGPSDisplay;
    private Button btnGPSExterno;

    //Variables que se van hacia el MAINACTIVITY
    public final static String BTN_GPS_EXTERNO = "btn gps externo";
    public final static String BTN_GPS_INTERNO = "btn gps interno";

    //Variables que se traen de el MAINACTIVITY
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //Variables locales
    private String mParam1;
    private String mParam2;

    private int GPS_selector_state;
    private int contadorDisplay;

    private OnFragmentInteractionListener mListener;


    public static GPSFragment newInstance(String param1, String param2) {
        GPSFragment fragment = new GPSFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public GPSFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GPS_selector_state = 0;
        contadorDisplay = 0;
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.gps_fragment, container, false);
        inicializarComponentes(rootview);
        return rootview;
    }

    private void inicializarComponentes(final View view) {
        txtGPSLatitud = (TextView)view.findViewById(R.id.txtGPSLatitud);
        txtGPSLongitud = (TextView)view.findViewById(R.id.txtGPSLongitud);
        txtGPSAltitud = (TextView)view.findViewById(R.id.txtGPSAltitud);
        txtGPSVelocidad = (TextView)view.findViewById(R.id.txtGPSVelocidad);
        txtGPSPrecision = (TextView)view.findViewById(R.id.txtGPSPrecision);
        txtGPSDisplay = (TextView)view.findViewById(R.id.txtGPSdisplay);


        btnGPSExterno = (Button) view.findViewById(R.id.btnGPSExterno);
        btnGPSExterno.setOnClickListener(this);

        switch (GPS_selector_state){
            case 0:
                btnGPSExterno.setText("GPS Externo");
                break;
            case 1:
                btnGPSExterno.setText("GPS Interno");
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
            case R.id.btnGPSExterno:
                switch (GPS_selector_state)
                {
                    case 0:
                        btnGPSExterno.setText("GPS Interno");
                        GPS_selector_state = 1;
                        uri = Uri.parse(BTN_GPS_EXTERNO+":");
                        break;
                    case 1:
                        btnGPSExterno.setText("GPS Externo");
                        GPS_selector_state = 0;
                        uri = Uri.parse(BTN_GPS_INTERNO+":");
                        break;
                }
                mListener.onFragmentInteraction(uri);
        }
    }


}
