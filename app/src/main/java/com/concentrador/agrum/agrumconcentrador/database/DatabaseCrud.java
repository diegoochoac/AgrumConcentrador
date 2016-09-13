package com.concentrador.agrum.agrumconcentrador.database;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by diego on 8/09/16.
 */
public class DatabaseCrud {

    private DatabaseHelper databaseHelper = null;
    private Dao<Usuario, Integer> usuarioDao;
    private Dao<Trabajo,Integer> trabajoDao;
    private Dao<Terreno,Integer> terrenoDao;
    private Dao<TipoEvento, Integer> tipoEventoDao;
    private Dao<Evento, Integer> eventoDao;

    //private Dao<Medicion,Integer> medicionDao;
    private Dao<Contratista,Integer> contratistaDao;


    Context thiscontext;

    public DatabaseCrud(Context context) {
        this.thiscontext = context;
        try {
            usuarioDao = getHelper().getUsuarioDao();
            trabajoDao = getHelper().getTrabajoDao();
            terrenoDao = getHelper().getTerrenoDao();
            //medicionDao = getHelper().getMedicionDao();
            contratistaDao = getHelper().getContratistaDao();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DatabaseHelper getHelper() {
        Log.i("DatabaseCrud", "DatabaseHelper");
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(thiscontext, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public void releaseHelper() {
        Log.i("DatabaseCrud", "releaseHelper");

        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    //<editor-fold desc="CRUD Usuario">
    public int crearUsuario(Usuario nuevo){
        try {
            return usuarioDao.create(nuevo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int actualizarUsuario(Usuario actualizar)
    {
        try {
            return usuarioDao.update(actualizar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarUsuario(Usuario eliminar)
    {
        try {
            return usuarioDao.delete(eliminar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Usuario> obtenerUsuarios()
    {
        try {
            return usuarioDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> obtenerUsuarioAutocompletar(String campo, String palabra){
        try {
            QueryBuilder<Usuario, Integer> db= usuarioDao.queryBuilder();
            Where<Usuario, Integer> where = db.where();
            where.like(campo, palabra+"%");
            return where.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String composeJSONfromSQLiteUsuario(){
        List<Usuario> listaNoSincronizados = null;
        try {
            listaNoSincronizados = usuarioDao.queryBuilder().where().eq("updateState","No").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().create();
        return gson.toJson(listaNoSincronizados);
    }

    public int dbSyncCountUsuario() {
        int count = 0;
        try {
            return (int) usuarioDao.queryBuilder().where().eq("updateState","No").countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void updateSyncStatusUsuario(String id, String status) {
        try {
            UpdateBuilder<Usuario,Integer> actualizar = usuarioDao.updateBuilder();
            actualizar.where().eq("usuario_id",id);
            actualizar.updateColumnValue("updateState",status);
            actualizar.update();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //</editor-fold>

    //<editor-fold desc="CRUD Contratista">
    public int crearContratista(Contratista nuevo){
        try {
            return contratistaDao.create(nuevo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int actualizarContratista(Contratista actualizar)
    {
        try {
            return contratistaDao.update(actualizar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarContratista(Contratista eliminar)
    {
        try {
            return contratistaDao.delete(eliminar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Contratista> obtenerContratistas()
    {
        try {
            return contratistaDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Contratista> obtenerContratistaAutocompletar(String campo, String palabra){
        try {
            QueryBuilder<Contratista, Integer> db= contratistaDao.queryBuilder();
            Where<Contratista, Integer> where = db.where();
            where.like(campo, palabra+"%");
            return where.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String composeJSONfromSQLiteContratista(){
        List<Contratista> listaNoSincronizados = null;
        try {
            listaNoSincronizados = contratistaDao.queryBuilder().where().eq("updateState","No").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().create();
        return gson.toJson(listaNoSincronizados);
    }

    public int dbSyncCountContratista() {
        int count = 0;
        try {
            return (int) contratistaDao.queryBuilder().where().eq("updateState","No").countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void updateSyncStatusContratistao(String id, String status) {
        try {
            UpdateBuilder<Contratista,Integer> actualizar = contratistaDao.updateBuilder();
            actualizar.where().eq("usuario_id",id);
            actualizar.updateColumnValue("updateState",status);
            actualizar.update();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //</editor-fold>

    //<editor-fold desc="CRUD Terreno">
    public int crearTerreno(Terreno nuevo){
        try {
            return terrenoDao.create(nuevo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int actualizarTerreno(Terreno actualizar)
    {
        try {
            return terrenoDao.update(actualizar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarTerreno(Terreno eliminar)
    {
        try {
            return terrenoDao.delete(eliminar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Terreno> obtenerTerrenos()
    {
        try {
            return terrenoDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Terreno> obtenerTerrenoAutocompletar(String campo, String palabra){
        try {
            QueryBuilder<Terreno, Integer> db= terrenoDao.queryBuilder();
            Where<Terreno, Integer> where = db.where();
            where.like(campo, palabra+"%");
            return where.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String composeJSONfromSQLiteTerrenos(){
        List<Terreno> listaNoSincronizados = null;
        try {
            listaNoSincronizados = terrenoDao.queryBuilder().where().eq("updateState","No").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().create();
        return gson.toJson(listaNoSincronizados);
    }

    public int dbSyncCountTerreno() {
        int count = 0;
        try {
            return (int) terrenoDao.queryBuilder().where().eq("updateState","No").countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void updateSyncStatusTerreno(String id, String status) {
        try {
            UpdateBuilder<Terreno,Integer> actualizar = terrenoDao.updateBuilder();
            actualizar.where().eq("usuario_id",id);
            actualizar.updateColumnValue("updateState",status);
            actualizar.update();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //</editor-fold>

    //<editor-fold desc="CRUD Evento">
    public int crearEvento(Evento nuevo){
        try {
            return eventoDao.create(nuevo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int actualizarEvento(Evento actualizar)
    {
        try {
            return eventoDao.update(actualizar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarEvento(Evento eliminar)
    {
        try {
            return eventoDao.delete(eliminar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Evento> obtenerEvento()
    {
        try {
            return eventoDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Evento> obtenerEventoAutocompletar(String campo, String palabra){
        try {
            QueryBuilder<Evento, Integer> db= eventoDao.queryBuilder();
            Where<Evento, Integer> where = db.where();
            where.like(campo, palabra+"%");
            return where.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String composeJSONfromSQLiteEvento(){
        List<Evento> listaNoSincronizados = null;
        try {
            listaNoSincronizados = eventoDao.queryBuilder().where().eq("updateState","No").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().create();
        return gson.toJson(listaNoSincronizados);
    }

    public int dbSyncCountEvento() {
        int count = 0;
        try {
            return (int) eventoDao.queryBuilder().where().eq("updateState","No").countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void updateSyncStatusEvento(String id, String status) {
        try {
            UpdateBuilder<Evento,Integer> actualizar = eventoDao.updateBuilder();
            actualizar.where().eq("usuario_id",id);
            actualizar.updateColumnValue("updateState",status);
            actualizar.update();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //</editor-fold>

    //<editor-fold desc="CRUD Evento">
    public int crearTipoEvento(TipoEvento nuevo){
        try {
            return tipoEventoDao.create(nuevo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int actualizarTipoEvento(TipoEvento actualizar)
    {
        try {
            return tipoEventoDao.update(actualizar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarEvento(TipoEvento eliminar)
    {
        try {
            return tipoEventoDao.delete(eliminar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<TipoEvento> obtenerTipoEvento()
    {
        try {
            return tipoEventoDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TipoEvento> obtenerTipoEventoAutocompletar(String campo, String palabra){
        try {
            QueryBuilder<TipoEvento, Integer> db= tipoEventoDao.queryBuilder();
            Where<TipoEvento, Integer> where = db.where();
            where.like(campo, palabra+"%");
            return where.query();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String composeJSONfromSQLiteTipoEvento(){
        List<TipoEvento> listaNoSincronizados = null;
        try {
            listaNoSincronizados = tipoEventoDao.queryBuilder().where().eq("updateState","No").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().create();
        return gson.toJson(listaNoSincronizados);
    }

    public int dbSyncCountTipoEvento() {
        int count = 0;
        try {
            return (int) tipoEventoDao.queryBuilder().where().eq("updateState","No").countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void updateSyncStatusTipoEvento(String id, String status) {
        try {
            UpdateBuilder<TipoEvento,Integer> actualizar = tipoEventoDao.updateBuilder();
            actualizar.where().eq("usuario_id",id);
            actualizar.updateColumnValue("updateState",status);
            actualizar.update();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //</editor-fold>

}
