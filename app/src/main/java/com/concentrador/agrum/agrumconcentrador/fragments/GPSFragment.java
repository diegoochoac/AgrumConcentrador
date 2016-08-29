package com.concentrador.agrum.agrumconcentrador.fragments;

import android.os.Bundle;
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

    private TextView txtGPSLatitud;
    private TextView txtGPSLongitud;
    private TextView txtGPSAltitud;
    private TextView txtGPSVelocidad;
    private TextView txtGPSPrecision;
    private TextView txtGPSDisplay;
    private TextView txtGPSSerial;

    private Button btnGPSExterno;

    private int GPS_selector_state;
    public final static String BTN_GPS_EXTERNO = "btn gps externo";
    public final static String BTN_GPS_INTERNO = "btn gps interno";


    public GPSFragment() {
        // Required empty public constructor
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

        //txtGPSSerial = (TextView)view.findViewById(R.id.txtSerial);


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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnGPSExterno:
                switch (GPS_selector_state)
                {
                    case 0:
                        btnGPSExterno.setText("GPS Interno");
                        GPS_selector_state = 1;
                        break;
                    case 1:
                        btnGPSExterno.setText("GPS Externo");
                        GPS_selector_state = 0;
                        break;
                }
                break;
        }


    }
}
