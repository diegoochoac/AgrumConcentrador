package com.concentrador.agrum.agrumconcentrador.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.concentrador.agrum.agrumconcentrador.R;
import com.concentrador.agrum.agrumconcentrador.database.Contratista;
import com.concentrador.agrum.agrumconcentrador.database.DatabaseHelper;
import com.concentrador.agrum.agrumconcentrador.database.Usuario;
import com.concentrador.agrum.agrumconcentrador.utils.ContratistaAdapter;
import com.concentrador.agrum.agrumconcentrador.utils.UsuarioAdapter;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MenuFragment1 extends Fragment implements OnClickListener {

    private DatabaseHelper databaseHelper = null;

    private EditText nombre, telefono, labor;
    private ImageView foto;
    private Button Btnagregar;
    private TextView contratista;

    Object select= null;

    private Dao<Contratista, Integer> contratistaDao;
    private List<Contratista> contratistaList;
    private ContratistaAdapter adapterContratista = null;

    private int request_code = 1;
    private Spinner spinnerContratista;
    Context thiscontext;



    public MenuFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.menu_fragment_crearusuario, container, false);
        inicializarComponentes(rootview);
        thiscontext = container.getContext();
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
        nombre = (EditText)view.findViewById(R.id.cmpNombre);
        telefono = (EditText)view.findViewById(R.id.cmpTelefono);
        labor = (EditText)view.findViewById(R.id.cmpLabor);
        contratista = (TextView)view.findViewById(R.id.cmpAgreContratista);

        foto = (ImageView)view.findViewById(R.id.imageView);
        foto.setOnClickListener(this);
        contratista.setOnClickListener(this);

        /*
        List<Contratista> contratistaList = null;
        try {
            final Dao<Contratista, Integer> contratistaDao = getHelper().getContratistaDao();
            contratistaList = contratistaDao.queryForAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        spinnerContratista = (Spinner)view.findViewById(R.id.spinnerContratista);
        ArrayAdapter<Contratista> dataAdapter = new ArrayAdapter<Contratista>(getActivity(),android.R.layout.simple_spinner_item, contratistaList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerContratista.setAdapter(dataAdapter);*/


        Btnagregar= (Button)view.findViewById(R.id.btnAgregarUsuario);
        Btnagregar.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Log.i("MenuFragment1", "onClick");


        switch (view.getId()) {

            case R.id.cmpAgreContratista:
                try {
                    contratistaDao = getHelper().getContratistaDao();
                    contratistaList = contratistaDao.queryForAll();
                    adapterContratista = new ContratistaAdapter(thiscontext, R.layout.row, contratistaList);
                    AlerDialogListContratista();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btnAgregarUsuario:
                agregarUsuario(
                        nombre.getText().toString(),
                        labor.getText().toString(),
                        telefono.getText().toString(),
                        String.valueOf(foto.getTag()),
                        //(Contratista) spinnerContratista.getSelectedItem()
                        (Contratista) select

                );
                Toast.makeText(view.getContext(),"Usuario Agregado", Toast.LENGTH_SHORT).show();
                limpiarCampos();
                break;
            case R.id.imageView:
                Intent intent=null;
                if(Build.VERSION.SDK_INT<19){
                    intent= new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                }else{
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                }
                intent.setType("image/*");
                startActivityForResult(intent,request_code);
                break;
        }
    }

    private void AlerDialogListContratista() {

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

        listview.setAdapter(adapterContratista);
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
                    contratistaDao =  getHelper().getContratistaDao();
                    contratistaList = contratistaDao.queryForEq(Contratista.NOMBRE,text);
                    adapterContratista = new ContratistaAdapter(thiscontext, R.layout.row, contratistaList);
                    listview.setAdapter(adapterContratista);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                select= adapterContratista.getItem(position);
                contratista.setText(adapterContratista.getItem(position).getContratistaName().toString());
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

    public void agregarUsuario(String name, String specialty, String phoneNumber, String avatarUri, Contratista contratista){
        Usuario usuario = new Usuario(name, specialty,phoneNumber,avatarUri,contratista);
        try {
            // Now, need to interact with StudentDetails table/object, so initialize DAO for that
            final Dao<Usuario, Integer> studentDao = getHelper().getUsuarioDao();
            studentDao.create(usuario);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void limpiarCampos(){
        nombre.getText().clear();
        labor.getText().clear();
        telefono.getText().clear();
        foto.setImageResource(R.drawable.ic_user);
        contratista.setText("Presione para seleccionar contratista");
        nombre.requestFocus();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK && requestCode == request_code){
            foto.setImageURI(data.getData());
            foto.setTag(data.getData());
        }
    }

}