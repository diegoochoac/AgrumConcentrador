package com.concentrador.agrum.agrumconcentrador.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.concentrador.agrum.agrumconcentrador.R;
import com.concentrador.agrum.agrumconcentrador.database.Contratista;
import com.concentrador.agrum.agrumconcentrador.database.DatabaseCrud;
import com.concentrador.agrum.agrumconcentrador.database.DatabaseHelper;
import com.concentrador.agrum.agrumconcentrador.database.Terreno;
import com.concentrador.agrum.agrumconcentrador.database.Usuario;
import com.concentrador.agrum.agrumconcentrador.utils.ContratistaAdapter;
import com.concentrador.agrum.agrumconcentrador.utils.TerrenoAdapter;
import com.concentrador.agrum.agrumconcentrador.utils.UsuarioAdapter;

import java.util.ArrayList;
import java.util.List;

public class ParametrosFragment extends Fragment implements OnClickListener{

    private DatabaseHelper databaseHelper = null;

    private TextView textViewHacienda, textViewSuerte, textViewContratista,textViewOperador;

    //BASE DE DATOS
    private DatabaseCrud database;
    private List<Usuario> usuarioList;
    private List<Contratista> contratistaList;
    private List<Terreno> terrenoList;

    private UsuarioAdapter adapterUsuario = null;
    private ContratistaAdapter adapterContratista = null;
    private TerrenoAdapter adapterTerreno = null;

    //Variables que se van hacia el MAINACTIVITY
    public final static String SET_HACIENDA = "Distacia hacienda";
    public final static String SET_LOTE = "Distacia lote";
    public final static String SET_CONTRATISTA = "Distacia contratista";
    public final static String SET_OPERADOR = "Distacia opeador";
    public final static String SET_PROFUNDIDAD_DESEADA = "Profundidad deseada";
    public final static String SET_EQ_AGRICOLA = "Profundidad eq Agricola";
    public static final String BTN_REGISTRO = "regiistro";
    public static final String BTN_PARAMETROS_MAQUINA = "PArametros maquibna";

    //Variables que se traen de el MAINACTIVITY
    public final static String ARG_HACIENDA = "Arg hacienda";
    public final static String ARG_LOTE = "Arg lote";
    public final static String ARG_CONTRATISTA = "Arg contratista";
    public final static String ARG_OPERADOR = "Arg opeador";

    //Variables locales
    private String hacienda;
    private String suerte;
    private String contratista;
    private String operador;

    private OnFragmentInteractionListener mListener;

    private int stateClick = -1;
    Context thiscontext;

    public static ParametrosFragment newInstance(String mHacienda, String mLote,String mContratista,String mOperador) {
        ParametrosFragment fragment = new ParametrosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HACIENDA, mHacienda);
        args.putString(ARG_LOTE, mLote);
        args.putString(ARG_OPERADOR, mOperador);
        args.putString(ARG_CONTRATISTA, mContratista);
        fragment.setArguments(args);
        return fragment;
    }

    public ParametrosFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Log.i("ParametrosFragment", "onCreate");
            hacienda = getArguments().getString(ARG_HACIENDA);
            suerte = getArguments().getString(ARG_LOTE);
            contratista = getArguments().getString(ARG_CONTRATISTA);
            operador = getArguments().getString(ARG_OPERADOR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.parametros_fragment, container, false);
        database = new DatabaseCrud(container.getContext());
        inicializarComponentes(rootview);
        thiscontext = container.getContext();
        return rootview;
    }


    private void inicializarComponentes(final View view) {

        textViewHacienda = (TextView)view.findViewById(R.id.cmpTextHacienda);
        textViewSuerte = (TextView)view.findViewById(R.id.cmpTextSuerte);
        textViewContratista = (TextView)view.findViewById(R.id.cmpTextContratista);
        textViewOperador = (TextView)view.findViewById(R.id.cmpTextOperador);

        textViewHacienda.setText(hacienda);
        textViewSuerte.setText(suerte);
        textViewContratista .setText(contratista);
        textViewOperador.setText(operador);

        textViewHacienda.setOnClickListener(this);
        textViewSuerte.setOnClickListener(this);
        textViewContratista.setOnClickListener(this);
        textViewOperador.setOnClickListener(this);

        ArrayList<String> lista = new ArrayList<String>();
        Spinner spinner1 = (Spinner) view.findViewById(R.id.spinnerProfundidad);
        //<editor-fold desc="Spinner1">
        lista.add("Sencillo");
        lista.add("Profundo");
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item, lista);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adaptador);

        spinner1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        System.out.println("Spinner1: position=" + position + " id=" + id);
                        String profundidadDeseada = "None";
                        switch (position) {
                            case 0:
                                profundidadDeseada = "Sencillo";
                                break;
                            case 1:
                                profundidadDeseada = "Profundo";
                                break;
                        }
                        Uri uri = Uri.parse(SET_PROFUNDIDAD_DESEADA + ":" + profundidadDeseada);
                        mListener.onFragmentInteraction(uri);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        System.out.println("Spinner1: unselected");
                    }
                });
        //</editor-fold>

        ArrayList<String> lista2 = new ArrayList<String>();
        Spinner spinner2 = (Spinner) view.findViewById(R.id.spinnerEqAgricola);
        //<editor-fold desc="Spinner 2">
        lista2.add("Tandem");
        lista2.add("Doble");
        lista2.add("Triple");
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<String>(this.getActivity(), R.layout.spinner_item, lista2);

        adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adaptador2);


        spinner2.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        System.out.println("Spinner2: position=" + position + " id=" + id);
                        String profundidadDeseada = "None";
                        switch (position) {
                            case 0:
                                profundidadDeseada = "Tandem";
                                break;
                            case 1:
                                profundidadDeseada = "Doble";
                                break;
                            case 2:
                                profundidadDeseada = "Triple";
                                break;
                        }
                        Uri uri = Uri.parse(SET_EQ_AGRICOLA + ":" + profundidadDeseada);
                        mListener.onFragmentInteraction(uri);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        System.out.println("Spinner1: unselected");
                    }
                });
        //</editor-fold>
    }


    void AlerDialogListUsuario(){
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

        listview.setAdapter(adapterUsuario);
        final AlertDialog alert = alertdialogbuilder.create();

        editText.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s){

            }
            public void beforeTextChanged(CharSequence s,int start, int count, int after){

            }
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String text = editText.getText().toString().toLowerCase().trim();
                usuarioList = database.obtenerUsuarioAutocompletar(Usuario.NOMBRE,text);
                Log.i("ParametrosFragment", "AlerDialogListUsuario numero:"+usuarioList.size());
                if(usuarioList.size()>0 && usuarioList != null){
                    adapterUsuario = new UsuarioAdapter(thiscontext, R.layout.row, usuarioList);
                    listview.setAdapter(adapterUsuario);
                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Object select= null;
                select= adapterUsuario.getItem(position).getUsuarioName();
                textViewOperador.setText(select.toString());
                Uri uri = Uri.parse(SET_OPERADOR +":"+ select.toString());
                mListener.onFragmentInteraction(uri);
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

    void AlerDialogListContratista(){
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

        listview.setAdapter(adapterContratista);
        final AlertDialog alert = alertdialogbuilder.create();

        editText.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s){

            }
            public void beforeTextChanged(CharSequence s,int start, int count, int after){

            }
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String text = editText.getText().toString().toLowerCase().trim();
                contratistaList = database.obtenerContratistaAutocompletar(Contratista.NOMBRE,text);
                Log.i("ParametrosFragment", "AlerDialogListUsuario numero:"+usuarioList.size());
                if(contratistaList.size()>0 && contratistaList != null){
                    adapterContratista = new ContratistaAdapter(thiscontext, R.layout.row, contratistaList);
                    listview.setAdapter(adapterContratista);
                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Object select= null;
                select= adapterContratista.getItem(position).getContratistaName();
                textViewContratista.setText(select.toString());
                Uri uri = Uri.parse(SET_CONTRATISTA +":"+ select.toString());
                mListener.onFragmentInteraction(uri);
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
        Log.i("ParametrosFragment", "AlerDialogListTerreno numero:"+ adapterTerreno.getCount());
        final AlertDialog alert = alertdialogbuilder.create();

        editText.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s){
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                Log.i("ParametrosFragment", "onTextChanged");
                String text = editText.getText().toString().toLowerCase().trim();

                switch (stateClick){
                    case 1:
                        terrenoList = database.obtenerTerrenoAutocompletar(Terreno.HACIENDA,text);
                        break;
                    case 2:
                        terrenoList = database.obtenerTerrenoAutocompletar(Terreno.SUERTE,text);
                        break;
                }
                if(terrenoList.size()>0 && terrenoList != null){
                    adapterTerreno = new TerrenoAdapter(thiscontext, R.layout.row, terrenoList);
                    listview.setAdapter(adapterTerreno);
                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Object select= null;
                Uri uri = Uri.parse("");
                switch (stateClick){
                    case 1:
                        select= adapterTerreno.getItem(position).getHacienda();
                        textViewHacienda.setText(select.toString());
                        uri = Uri.parse(SET_HACIENDA +":"+ select.toString());
                        mListener.onFragmentInteraction(uri);
                        break;
                    case 2:
                        select= adapterTerreno.getItem(position).getSte();
                        textViewSuerte.setText(select.toString());
                        uri = Uri.parse(SET_LOTE +":"+ select.toString() );
                        mListener.onFragmentInteraction(uri);
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
        Log.i("ParametrosFragment", "onClick");
        Uri uri = Uri.parse("");
        switch (view.getId()) {
            case R.id.cmpTextHacienda:
                terrenoList = database.obtenerTerrenos();
                if(terrenoList.size()>0 && terrenoList != null){
                    Log.i("ParametrosFragment", "onClick cmpTextHacienda");
                    adapterTerreno = new TerrenoAdapter(thiscontext, R.layout.row, terrenoList);
                    AlerDialogListTerreno();
                }
                stateClick = 1;
                break;

            case R.id.cmpTextSuerte:
                terrenoList = database.obtenerTerrenos();
                if(terrenoList.size()>0 && terrenoList != null){
                    Log.i("ParametrosFragment", "onClick cmpTextSuerte");
                    adapterTerreno = new TerrenoAdapter(thiscontext, R.layout.row, terrenoList);
                    AlerDialogListTerreno();
                }
                stateClick = 2;
                break;

            case R.id.cmpTextContratista:
                contratistaList = database.obtenerContratistas();
                if(contratistaList.size()>0 && contratistaList != null){
                    Log.i("ParametrosFragment", "onClick cmpTextOperador");
                    adapterContratista = new ContratistaAdapter(thiscontext, R.layout.row, contratistaList);
                    AlerDialogListContratista();
                }
                stateClick = 3;
                break;

            case R.id.cmpTextOperador:
                usuarioList = database.obtenerUsuarios();
                if(usuarioList.size()>0 && usuarioList != null){
                    Log.i("ParametrosFragment", "onClick cmpTextOperador");
                    adapterUsuario = new UsuarioAdapter(thiscontext, R.layout.row, usuarioList);
                    AlerDialogListUsuario();
                }
                stateClick = 4;
                break;

            case R.id.btnRegistro:
                uri = Uri.parse(BTN_REGISTRO+":");
                mListener.onFragmentInteraction(uri);
                break;

            case R.id.btnParametrosMaquina:
                uri = Uri.parse(BTN_PARAMETROS_MAQUINA+":");
                mListener.onFragmentInteraction(uri);
                break;

        }
    }



}