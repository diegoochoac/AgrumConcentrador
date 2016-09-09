package com.concentrador.agrum.agrumconcentrador.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by diego on 26/08/16.
 */
//@DatabaseTable(tableName = "terreno")
public class Terreno implements Serializable {

    public static final String ID = "terreno_id";
    public static final String HACIENDA = "hacienda";
    public static final String SUERTE = "suerte";

    @DatabaseField(generatedId = true, columnName = ID)
    private int terrenoId;

    @DatabaseField
    private String Cod;

    @DatabaseField (columnName = HACIENDA)
    private String Hacienda;

    @DatabaseField(columnName = SUERTE)
    private String Ste;

    @DatabaseField
    private String Variedad;

    @DatabaseField
    private String ZonaAdmi;

    @DatabaseField
    private String Area;

    public Terreno() {
    }

    public Terreno(final String cod, final String hacienda, final String ste, final String variedad,
                   final String zonaAdmi, final String area) {
        this.Cod = cod;
        this.Hacienda = hacienda;
        this.Ste = ste;
        this.Variedad = variedad;
        this.ZonaAdmi = zonaAdmi;
        this.Area = area;
    }

    //<editor-fold desc="Metodos Get-Set">
    public int getTerrenoId() {
        return terrenoId;
    }

    public void setTerrenoId(int terrenoId) {
        this.terrenoId = terrenoId;
    }

    public String getCod() {
        return Cod;
    }

    public void setCod(String cod) {
        Cod = cod;
    }

    public String getHacienda() {
        return Hacienda;
    }

    public void setHacienda(String hacienda) {
        Hacienda = hacienda;
    }

    public String getSte() {
        return Ste;
    }

    public void setSte(String ste) {
        Ste = ste;
    }

    public String getVariedad() {
        return Variedad;
    }

    public void setVariedad(String variedad) {
        Variedad = variedad;
    }

    public String getZonaAdmi() {
        return ZonaAdmi;
    }

    public void setZonaAdmi(String zonaAdmi) {
        ZonaAdmi = zonaAdmi;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }
    //</editor-fold>
}
