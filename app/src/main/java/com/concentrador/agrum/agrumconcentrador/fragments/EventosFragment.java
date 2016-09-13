package com.concentrador.agrum.agrumconcentrador.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.concentrador.agrum.agrumconcentrador.MainActivity;
import com.concentrador.agrum.agrumconcentrador.MenuActivity;
import com.concentrador.agrum.agrumconcentrador.R;
import com.concentrador.agrum.agrumconcentrador.database.DatabaseCrud;
import com.concentrador.agrum.agrumconcentrador.database.Terreno;
import com.concentrador.agrum.agrumconcentrador.database.TipoEvento;
import com.concentrador.agrum.agrumconcentrador.database.Usuario;
import com.concentrador.agrum.agrumconcentrador.utils.TerrenoAdapter;

import java.util.List;

/**
 * Created by diego on 12/09/16.
 */
public class EventosFragment extends Fragment implements OnClickListener{

    private Button btnSelecTipoEven;
    private ImageView imageHome;

    //BASE DE DATOS
    private DatabaseCrud database;
    private List<TipoEvento> tipoEventoList;
    private ArrayAdapter<TipoEvento> adapterTipoEvento;

    //Variables que se van hacia el MAINACTIVITY
    public final static String SET_EVENTO = "Tipo evento";

    Context thiscontext;
    private OnFragmentInteractionListener mListener;

    public EventosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.eventos_fragment, container, false);
        database = new DatabaseCrud(container.getContext());
        inicializarComponentes(rootview);
        thiscontext = container.getContext();
        return rootview;
    }


    private void inicializarComponentes(final View view) {
        btnSelecTipoEven= (Button)view.findViewById(R.id.btnSeleEvento);
        btnSelecTipoEven.setOnClickListener(this);
        imageHome = (ImageView)view.findViewById(R.id.imageHome);
        imageHome.setOnClickListener(this);
    }

    void AlerDialogListEvento(){
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

        listview.setAdapter(adapterTipoEvento);
        Log.i("ParametrosFragment", "AlerDialogListTerreno numero:"+ adapterTipoEvento.getCount());
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
                tipoEventoList = database.obtenerTipoEventoAutocompletar(TipoEvento.NOMBRE,text);

                if(tipoEventoList.size()>0 && tipoEventoList != null){
                    //adapterTerreno = new TerrenoAdapter(thiscontext, R.layout.row, tipoEventoList);
                    adapterTipoEvento = new ArrayAdapter<TipoEvento>(thiscontext,android.R.layout.simple_list_item_1,tipoEventoList);
                    listview.setAdapter(adapterTipoEvento);
                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Object select= null;
                Uri uri = Uri.parse("");
                select= adapterTipoEvento.getItem(position).getTipoEventoName();
                btnSelecTipoEven.setText(select.toString());
                uri = Uri.parse(SET_EVENTO +":"+ select.toString());
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
    public void onDestroyView() {
        Log.i("EventosFragment", "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onClick(View view) {

        Uri uri = Uri.parse("");
        switch (view.getId()) {
            case R.id.btnSeleEvento:
                Log.i("EventosFragment", "onClick btnSeleEvento");
                tipoEventoList = database.obtenerTipoEvento();
                if(tipoEventoList.size()>0 && tipoEventoList != null){
                    adapterTipoEvento = new ArrayAdapter<TipoEvento>(thiscontext,android.R.layout.simple_list_item_1,tipoEventoList);
                    //adapterTerreno = new TerrenoAdapter(thiscontext, R.layout.row, terrenoList);
                    AlerDialogListEvento();
                }
                break;
            case R.id.imageHome:
                Log.i("EventosFragment", "onClick imageHome");
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
                break;

        }

    }
}
