package com.concentrador.agrum.agrumconcentrador.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by diego on 26/08/16.
 */
@DatabaseTable(tableName = "trabajo")
public class Trabajo implements Serializable {

    @DatabaseField(generatedId = true)
    private String id;

    @DatabaseField(index = true, canBeNull = false)
    private String name;

    public Trabajo() {
    }

    public Trabajo(String name) {

        this.name = name;

    }

    //<editor-fold desc="Metodos Get">
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    //</editor-fold>

    //<editor-fold desc="Metodos Set">
    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>
}
