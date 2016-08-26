package com.concentrador.agrum.agrumconcentrador.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.concentrador.agrum.agrumconcentrador.R;
import com.concentrador.agrum.agrumconcentrador.database.Usuario;
import com.concentrador.agrum.agrumconcentrador.database.UsuarioReceiver;

public class MenuFragment1 extends Fragment implements OnClickListener {

    private EditText nombre, telefono, labor;
    private ImageView foto;
    private Button Btnagregar;
    private int request_code = 1;

    public MenuFragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.menu_fragment1, container, false);
        inicializarComponentes(rootview);
        return rootview;
    }

    private void inicializarComponentes(final View view) {
        nombre = (EditText)view.findViewById(R.id.cmpNombre);
        telefono = (EditText)view.findViewById(R.id.cmpTelefono);
        labor = (EditText)view.findViewById(R.id.cmpLabor);

        foto = (ImageView)view.findViewById(R.id.imageView);
        foto.setOnClickListener(this);

        Btnagregar= (Button)view.findViewById(R.id.btnAgregar);
        Btnagregar.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Log.i("MenuFragment1", "onClick");

        switch (view.getId()) {
            case R.id.btnAgregar:
                agregarUsuario(
                        nombre.getText().toString(),
                        labor.getText().toString(),
                        telefono.getText().toString(),
                        String.valueOf(foto.getTag())
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

    public void agregarUsuario(String name,String specialty, String phoneNumber, String avatarUri){
        Usuario usuario = new Usuario(name, specialty,phoneNumber,avatarUri);
        Intent intent = new Intent("listacontactos");
        intent.putExtra("operacion", UsuarioReceiver.USUARIO_AGREGADO);
        intent.putExtra("datos",usuario);
        getActivity().sendBroadcast(intent);
    }

    public void limpiarCampos(){
        nombre.getText().clear();
        labor.getText().clear();
        telefono.getText().clear();
        foto.setImageResource(R.drawable.ic_user);
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