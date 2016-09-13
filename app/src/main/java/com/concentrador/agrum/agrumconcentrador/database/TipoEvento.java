package com.concentrador.agrum.agrumconcentrador.database;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by diego on 12/09/16.
 */
public class TipoEvento implements Serializable {

    public static final String ID = "tipoEvento_id";
    public static final String NOMBRE = "tipoEvento_name";

    @DatabaseField(generatedId = true, columnName = ID)
    private int tipoEventoId;

    @DatabaseField(columnName = NOMBRE)
    private String tipoEventoName;

    public TipoEvento() {
    }

    public TipoEvento(final String tipoEventoName) {
        this.tipoEventoName = tipoEventoName;
    }

    //<editor-fold desc="Metodos Get-Set">
    public int getTipoEventoId() {
        return tipoEventoId;
    }

    public void setTipoEventoId(int tipoEventoId) {
        this.tipoEventoId = tipoEventoId;
    }

    public String getTipoEventoName() {
        return tipoEventoName;
    }

    public void setTipoEventoName(String tipoEventoName) {
        this.tipoEventoName = tipoEventoName;
    }
    //</editor-fold>
}
