package com.concentrador.agrum.agrumconcentrador.database;

/**
 * Created by diego on 26/08/16.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.concentrador.agrum.agrumconcentrador.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.field.types.StringBytesType;
import com.j256.ormlite.stmt.query.In;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "concentrador.db";
    private static final int DATABASE_VERSION = 1;

    //Objeto Dao qye se utiliza para acceder a la tabla usuario
    private Dao<Usuario,Integer> usuarioDao;
    private Dao<Contratista, Integer> contratistaDao;
    private Dao<Trabajo,Integer> trabajoDao;
    private Dao<Terreno,Integer> terrenoDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    /**
     * Metodo invocado cuando la base de datos es creada. Usualmente se hacen llamadas a los metodos
     * createTable para crear las tablas que almacenaran los datos
     * @param db
     * @param source
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource source) {
        try {
            Log.i(DatabaseHelper.class.getSimpleName(), "onCreate()");
            TableUtils.createTable(source,Usuario.class);
            TableUtils.createTable(source,Contratista.class);
            TableUtils.createTable(source,Trabajo.class);
            TableUtils.createTable(source,Terreno.class);
        }catch (SQLException ex) {
            Log.e(DatabaseHelper.class.getSimpleName(),"Imposible crear base de datos",ex);
            throw  new RuntimeException(ex);
        }
    }

    /**
     * Este metodo es invocado cuando la aplicacion es actualizada y tiene un numero de version
     * superio. permite el ajuste a los mtados para alinearse con la nueva version
     * @param db
     * @param source
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource source, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getSimpleName(), "onUpgrade()");
            TableUtils.dropTable(source,Usuario.class, true);
            TableUtils.dropTable(source,Contratista.class, true);
            TableUtils.dropTable(source,Trabajo.class, true);
            TableUtils.dropTable(source,Terreno.class, true);
            onCreate(db, source);
        } catch (SQLException ex) {
            Log.e(DatabaseHelper.class.getSimpleName(),"Imposible actualizar base de datos",ex);
            throw  new RuntimeException(ex);
        }

    }

    //<editor-fold desc="Metodos Get">

    public Dao<Usuario, Integer> getUsuarioDao()  throws SQLException{
        if(usuarioDao==null){
            usuarioDao = getDao(Usuario.class);
        }
        return usuarioDao;
    }

    public Dao<Contratista, Integer> getContratistaDao()  throws SQLException{
        if(contratistaDao==null){
            contratistaDao = getDao(Contratista.class);
        }
        return contratistaDao;
    }

    public Dao<Trabajo, Integer> getTrabajoDao() throws SQLException {
        if(trabajoDao==null){
            trabajoDao = getDao(Trabajo.class);
        }
        return trabajoDao;
    }

    public Dao<Terreno, Integer> getTerrenoDao() throws SQLException {
        if(terrenoDao==null){
            terrenoDao = getDao(Terreno.class);
        }
        return terrenoDao;
    }
    //</editor-fold>



}
