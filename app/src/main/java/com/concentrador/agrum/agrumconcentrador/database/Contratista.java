package com.concentrador.agrum.agrumconcentrador.database;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by diego on 2/09/16.
 */
public class Contratista implements Serializable{

    public static final String ID = "contratista_id";
    public static final String NOMBRE = "contratista_name";

    @DatabaseField(generatedId = true, columnName = ID)
    private int usuarioId;

    @DatabaseField(columnName = NOMBRE)
    private String contratistaName;

    public Contratista() {
    }

    public Contratista(final String name) {
        this.contratistaName = name;
    }

    //<editor-fold desc="Metodos Get-Set">
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getContratistaName() {
        return contratistaName;
    }

    public void setContratistaName(String contratistaName) {
        this.contratistaName = contratistaName;
    }
    //</editor-fold>
}
