package com.concentrador.agrum.agrumconcentrador.database;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;

/**
 * Created by diego on 26/08/16.
 */
public class UsuarioReceiver extends BroadcastReceiver {

    public static final int USUARIO_AGREGADO = 1;
    public static final int USUARIO_ELIMINADO = 2;
    public static final int USUARIO_ACTUALIZADO = 3;

    private final OrmLiteBaseActivity<DatabaseHelper> activity;
    private final ArrayAdapter<Usuario> adapter;

    public UsuarioReceiver(OrmLiteBaseActivity<DatabaseHelper> ormLiteBaseActivity, ArrayAdapter<Usuario> adapter) {
        this.activity = ormLiteBaseActivity;
        this.adapter = adapter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int operacion = intent.getIntExtra("operacion",-1);

        switch (operacion){
            case USUARIO_AGREGADO:
                agregarUsuario(intent);
                break;
            case USUARIO_ELIMINADO:
                eliminarUsuario(intent);
                break;
            case USUARIO_ACTUALIZADO:
                actualizarUsuario(intent);
                break;
        }
    }

    private void agregarUsuario(Intent intent) {
        Usuario usuario = (Usuario)intent.getSerializableExtra("datos");
        if(activity != null){
            DatabaseHelper helper = activity.getHelper();
            RuntimeExceptionDao<Usuario,Integer> dao = helper.getUsuarioRuntimeDao();
            dao.create(usuario);
        }
        adapter.add(usuario);
    }

    private void eliminarUsuario(Intent intent) {
        ArrayList<Usuario> lista =(ArrayList<Usuario>)intent.getSerializableExtra("datos");
        if(activity != null){
            DatabaseHelper helper = activity.getHelper();
            RuntimeExceptionDao<Usuario,Integer> dao = helper.getUsuarioRuntimeDao();
            dao.delete(lista);
        }
        for(Usuario c : lista){
            adapter.remove(c);
        }
    }

    private void actualizarUsuario(Intent intent) {
        Usuario usuario = (Usuario)intent.getSerializableExtra("datos");
        if(activity != null){
            DatabaseHelper helper = activity.getHelper();
            RuntimeExceptionDao<Usuario,Integer> dao = helper.getUsuarioRuntimeDao();
            dao.update(usuario);
        }
        int posicion = adapter.getPosition(usuario);
        adapter.insert(usuario, posicion);
    }


}
